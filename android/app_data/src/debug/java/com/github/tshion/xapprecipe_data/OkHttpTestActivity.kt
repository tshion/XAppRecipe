package com.github.tshion.xapprecipe_data

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.LinearLayout
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import com.github.tshion.xapprecipe_core.repositories.ToDoTaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class OkHttpTestActivity : Activity() {

    var repository: ToDoTaskRepository? = null
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = VERTICAL
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
        }, MATCH_PARENT, WRAP_CONTENT)

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
        }, MATCH_PARENT, WRAP_CONTENT)

        setContentView(root)
    }


    private fun showResult(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    companion object {
        const val testFatal = "can't recover"
        const val testSuccess = "success"
    }
}
