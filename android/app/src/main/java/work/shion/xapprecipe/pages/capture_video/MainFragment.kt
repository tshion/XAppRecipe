package work.shion.xapprecipe.pages.capture_video

import android.Manifest
import android.annotation.SuppressLint
import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.core.VideoCapture
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import permissions.dispatcher.ktx.constructPermissionsRequest
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.PageCaptureVideoBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * ビデオ撮影
 */
class MainFragment : Fragment(R.layout.page_capture_video) {

    companion object {
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val TAG = "CameraXBasic"
    }


    private var binding: PageCaptureVideoBinding? = null
    private lateinit var cameraExecutor: ExecutorService
    private var isRecording = false
    private lateinit var outputDirectory: File
    private var videoCapture: VideoCapture? = null


    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = PageCaptureVideoBinding.bind(view)
        binding?.pageCaptureVideoButton?.setOnClickListener {
            val videoCapture = videoCapture ?: return@setOnClickListener

            if (!isRecording) {
                val videoFile = File(
                    outputDirectory,
                    "${
                        SimpleDateFormat(FILENAME_FORMAT, Locale.JAPAN)
                            .format(System.currentTimeMillis())
                    }.mp4"
                )

                val outputOptions = VideoCapture.OutputFileOptions.Builder(videoFile)
                    .build()

                videoCapture.startRecording(
                    outputOptions,
                    ContextCompat.getMainExecutor(requireContext()),
                    object : VideoCapture.OnVideoSavedCallback {

                        override fun onError(
                            videoCaptureError: Int,
                            message: String,
                            cause: Throwable?
                        ) {
                            Log.e(TAG, "Video capture failed: ${cause?.message}", cause)
                        }

                        override fun onVideoSaved(outputFileResults: VideoCapture.OutputFileResults) {
                            val savedUri = Uri.fromFile(videoFile)
                            val msg = "Video capture succeeded: $savedUri"
                            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                            Log.d(TAG, msg)
                        }
                    }
                )
            } else {
                videoCapture.stopRecording()
            }

            isRecording = !isRecording
        }

        cameraExecutor = Executors.newSingleThreadExecutor()
        outputDirectory = getOutputDirectory()

        constructPermissionsRequest(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            onShowRationale = { it.proceed() },
            onPermissionDenied = null,
            onNeverAskAgain = null,
        ) {
            val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
            ProcessCameraProvider.getInstance(requireContext()).addListener(
                {
                    val cameraProvider = cameraProviderFuture.get()

                    videoCapture = VideoCapture.Builder().apply {
                        setAudioRecordSource(MediaRecorder.AudioSource.MIC)
                    }.build()

                    val preview = Preview.Builder()
                        .build()
                        .also {
                            it.setSurfaceProvider(binding!!.pageCaptureVideoPreview.surfaceProvider)
                        }

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            viewLifecycleOwner,
                            CameraSelector.DEFAULT_BACK_CAMERA,
                            preview,
                            videoCapture,
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
