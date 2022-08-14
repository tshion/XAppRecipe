using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.ModelBinding;
using Swashbuckle.AspNetCore.Annotations;
using XApp.Entities;
using XApp.Repositories;
using XApp.Types;

namespace XAppApi.Controllers.ToDoTasks
{
    /// <summary>
    /// ToDo 関連を取り扱うController
    /// </summary>
    [ApiController]
    [Route("v1/todo")]
    public class ToDoTaskController : ControllerBase
    {
        private readonly ToDoTaskRepository _repository;



        public ToDoTaskController(
            ToDoTaskRepository repository
        )
        {
            _repository = repository;
        }



        [HttpGet]
        [SwaggerOperation(Summary = "登録したToDo 一覧の取得")]
        [SwaggerResponse(StatusCodes.Status200OK, "成功", typeof(GetToDoTaskResponse))]
        public IActionResult Get()
        {
            var data = _repository.Load()
                .Select(item => new ToDoTask
                {
                    id = item.Id.Token,
                    is_finish = item.IsFinished,
                    title = item.Title,
                    update_date = item.UpdateDate,
                });
            return Ok(new GetToDoTaskResponse()
            {
                items = data,
            });
        }

        [HttpPost]
        [SwaggerOperation(Summary = "ToDo 新規登録")]
        [SwaggerResponse(StatusCodes.Status200OK, "成功")]
        [SwaggerResponse(StatusCodes.Status422UnprocessableEntity, "指定パラメータ不備による失敗")]
        public IActionResult Post(
            [FromBody, BindRequired] PostToDoTaskRequest request
        )
        {
            if (!ModelState.IsValid)
            {
                return UnprocessableEntity(ModelState);
            }

            var candidate = new ToDoTaskEntity(
                Id: IdTextType.NewId(),
                IsFinished: false,
                Title: request.title,
                UpdateDate: DateTime.UtcNow
            );
            _repository.Save(candidate);
            return Ok();
        }

        [HttpPut("{id}")]
        [SwaggerOperation(Summary = "ToDo 編集")]
        [SwaggerResponse(StatusCodes.Status200OK, "成功")]
        [SwaggerResponse(StatusCodes.Status422UnprocessableEntity, "指定パラメータ不備による失敗")]
        public IActionResult Put(
            [FromRoute, SwaggerParameter("識別番号", Required = true)] string id,
            [FromBody, BindRequired] PutToDoTaskRequest request
        )
        {
            if (string.IsNullOrWhiteSpace(id))
            {
                return UnprocessableEntity();
            }


            var target = new ToDoTaskEntity(
                Id: IdTextType.Parse(id),
                IsFinished: request.is_finish,
                Title: request.title,
                UpdateDate: DateTime.UtcNow
            );
            _repository.Save(target);
            return Ok();
        }

        [HttpDelete("{id}")]
        [SwaggerOperation(Summary = "ToDo 削除")]
        [SwaggerResponse(StatusCodes.Status200OK, "成功")]
        [SwaggerResponse(StatusCodes.Status422UnprocessableEntity, "指定パラメータ不備による失敗")]
        public IActionResult Delete(
            [FromRoute, SwaggerParameter("識別番号", Required = true)] string id
        )
        {
            if (string.IsNullOrWhiteSpace(id))
            {
                return UnprocessableEntity();
            }

            var target = new ToDoTaskEntity(
                Id: IdTextType.Parse(id),
                false,
                "",
                DateTime.UtcNow
            );
            _repository.Delete(target);
            return Ok();
        }
    }
}
