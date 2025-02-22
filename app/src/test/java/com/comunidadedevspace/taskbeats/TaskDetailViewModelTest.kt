package com.comunidadedevspace.taskbeats

import com.comunidadedevspace.taskbeats.data.Task
import com.comunidadedevspace.taskbeats.data.TaskDao
import com.comunidadedevspace.taskbeats.presentation.ActionType
import com.comunidadedevspace.taskbeats.presentation.TaskAction
import com.comunidadedevspace.taskbeats.presentation.TaskDetailViewModel
import com.comunidadedevspace.taskbeats.presentation.TaskListViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify


class TaskDetailViewModelTest {
    private val taskDao: TaskDao = mock()

    private val underTest: TaskDetailViewModel by lazy {
        TaskDetailViewModel(taskDao)
    }
    @Test
    fun update_task() = runTest {
        //Given
        val task = Task(
            id = 1,
            title = "Title",
            description = "description"
        )

        val taskAction = TaskAction(
            task = task,
            actionType = ActionType.UPDATE.name
        )
        //When
        underTest.execute(taskAction)

        //Then
        verify(taskDao).update(task)
    }

    @Test
    fun delete_task() = runTest {
        //Given
        val task = Task(
            id = 1,
            title = "Title",
            description = "description"
        )

        val taskAction = TaskAction(
            task = task,
            actionType = ActionType.DELETE.name
        )
        //When
        underTest.execute(taskAction)

        //Then
        verify(taskDao).deleteById(task.id)
    }

    @Test
    fun create_task() = runTest {
        //Given
        val task = Task(
            id = 1,
            title = "Title",
            description = "description"
        )

        val taskAction = TaskAction(
            task = task,
            actionType = ActionType.CREATE.name
        )
        //When
        underTest.execute(taskAction)

        //Then
        verify(taskDao).insert(task)
    }
}