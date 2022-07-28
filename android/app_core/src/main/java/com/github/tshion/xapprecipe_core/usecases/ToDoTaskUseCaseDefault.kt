package com.github.tshion.xapprecipe_core.usecases

import com.github.tshion.xapprecipe_core.entities.ToDoTaskEntity
import com.github.tshion.xapprecipe_core.repositories.ToDoTaskRepository
import com.github.tshion.xapprecipe_core.validators.ToDoTaskValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * やること情報の取り扱い方法の標準実装
 */
public class ToDoTaskUseCaseDefault(
    private val todoTaskRepository: ToDoTaskRepository,
) : ToDoTaskUseCase {

    private val _stream = MutableStateFlow<List<ToDoTaskEntity>>(emptyList())

    /** やること情報の変化が流れてくるストリーム */
    override val stream: StateFlow<List<ToDoTaskEntity>> = _stream


    /**
     * 新規作成
     *
     * @param todoName やること名
     */
    override suspend fun create(todoName: String?) {
        ToDoTaskValidator.preCheckTitle(todoName)?.also { throw it }

        todoTaskRepository.register(todoName!!)
        refreshStream()
    }

    /** タスクの完了 */
    override suspend fun done(target: ToDoTaskEntity) {
        val newState = target.copy(isFinish = true)
        todoTaskRepository.edit(newState)
        todoTaskRepository.remove(newState)
        refreshStream()
    }

    /**
     * 編集
     *
     * @param target 対象データ
     * @param newToDoName 変更後のやること名
     */
    override suspend fun edit(target: ToDoTaskEntity, newToDoName: String?) {
        ToDoTaskValidator.preCheckTitle(newToDoName)?.also { throw it }

        val newState = target.copy(title = newToDoName!!)
        todoTaskRepository.edit(newState)
        refreshStream()
    }

    /** タスクを諦める */
    override suspend fun giveUp(target: ToDoTaskEntity) {
        todoTaskRepository.remove(target)
        refreshStream()
    }

    /** 最新情報をストリームに流す */
    override suspend fun refreshStream() {
        _stream.value = todoTaskRepository.fetch()
    }
}
