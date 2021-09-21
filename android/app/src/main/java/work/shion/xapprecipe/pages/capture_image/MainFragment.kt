package work.shion.xapprecipe.pages.capture_image

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import permissions.dispatcher.ktx.constructPermissionsRequest
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.PageCaptureImageBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * 画像撮影
 * 参考文献: [Getting Started with CameraX](https://developer.android.com/codelabs/camerax-getting-started)
 */
class MainFragment : Fragment(R.layout.page_capture_image) {

    companion object {
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val TAG = "CameraXBasic"
    }


    private var binding: PageCaptureImageBinding? = null
    private lateinit var cameraExecutor: ExecutorService
    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = PageCaptureImageBinding.bind(view)
        binding?.pageCaptureImageButton?.setOnClickListener {
            val imageCapture = imageCapture ?: return@setOnClickListener

            val photoFile = File(
                outputDirectory,
                "${
                    SimpleDateFormat(FILENAME_FORMAT, Locale.JAPAN)
                        .format(System.currentTimeMillis())
                }.jpg"
            )

            val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile)
                .build()

            imageCapture.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(requireContext()),
                object : ImageCapture.OnImageSavedCallback {

                    override fun onError(exception: ImageCaptureException) {
                        Log.e(TAG, "Photo capture failed: ${exception.message}", exception)
                    }

                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        val savedUri = Uri.fromFile(photoFile)
                        val msg = "Photo capture succeeded: $savedUri"
                        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                        Log.d(TAG, msg)
                    }
                }
            )
        }

        cameraExecutor = Executors.newSingleThreadExecutor()
        outputDirectory = getOutputDirectory()

        constructPermissionsRequest(
            Manifest.permission.CAMERA,
            onShowRationale = { it.proceed() },
            onPermissionDenied = null,
            onNeverAskAgain = null,
        ) {
            val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
            ProcessCameraProvider.getInstance(requireContext()).addListener(
                {
                    val cameraProvider = cameraProviderFuture.get()

                    imageCapture = ImageCapture.Builder()
                        .build()

                    val preview = Preview.Builder()
                        .build()
                        .also {
                            it.setSurfaceProvider(binding!!.pageCaptureImagePreview.surfaceProvider)
                        }

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            viewLifecycleOwner,
                            CameraSelector.DEFAULT_BACK_CAMERA,
                            preview,
                            imageCapture,
                        )
                    } catch (ex: Throwable) {
                    }
                },
                ContextCompat.getMainExecutor(requireContext()),
            )
        }.launch()
    }

    override fun onDestroyView() {
        binding = null
        cameraExecutor.shutdown()
        super.onDestroyView()
    }


    private fun getOutputDirectory(): File {
        val mediaDir = activity?.externalMediaDirs?.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) {
            mediaDir
        } else {
            activity?.filesDir!!
        }
    }
}
