package work.shion.androidrecipe

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import work.shion.androidrecipe.repositories.ToDoRepository

class OkHttpTestActivity : Activity() {

    companion object {
        const val testFatal = "can't recover"
        const val testSuccess = "success"
    }

    var repository: ToDoRepository? = null
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        root.addView(Button(this).apply {
            setOnClickListener {
                scope.launch {
                    runCatching {
                        repository?.fetch()
                    }.onSuccess {
                        showResult(it?.getOrNull(0)?.title ?: "")
                    }.onFailure {
                        showResult("失敗")
                    }
                }
            }
            text = testSuccess
        }, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        root.addView(Button(this).apply {
            setOnClickListener {
                scope.launch {
                    runCatching {
                        repository?.fetch()
                    }.onSuccess {
                        showResult(it?.getOrNull(0)?.title ?: "")
                    }.onFailure {
                        showResult("失敗")
                    }
                }
            }
            text = testFatal
        }, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        setContentView(root)
    }


    private fun showResult(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
