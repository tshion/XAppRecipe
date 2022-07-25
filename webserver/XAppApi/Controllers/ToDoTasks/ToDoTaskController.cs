using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.ModelBinding;
using Swashbuckle.AspNetCore.Annotations;

namespace XAppApi.Controllers.ToDoTasks
{
    /// <summary>
    /// ToDo 関連を取り扱うController
    /// </summary>
    [ApiController]
    [Route("v1/todo")]
    public class ToDoTaskController : ControllerBase
    {
        // TODO: HTTP 要求毎にController 生成されるので、Model 側に持っていきたい
        private readonly List<ToDoTask> _store = new()
        {
            new()
            {
                id = "1",
                is_finish = false,
                title = "やることその１",
                update_time = DateTime.UtcNow,
            },
            new()
            {
                id = "2",
                is_finish = true,
                title = "やることその２",
                update_time = DateTime.UtcNow,
            },
            new()
            {
                id = "4",
                title = "やることその４",
                update_time = DateTime.UtcNow,
            },
            new()
            {
                id = "5",
                is_finish = true,
                update_time = DateTime.UtcNow,
            },
        };



        [HttpGet]
        [SwaggerOperation(Summary = "登録したToDo 一覧の取得")]
        [SwaggerResponse(StatusCodes.Status200OK, "成功", typeof(GetToDoTaskResponse))]
        public IActionResult Get()
        {
            return Ok(new GetToDoTaskResponse()
            {
                items = _store.ToList(),
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

            var candidate = new ToDoTask
            {
                id = (_store.Max(x => int.Parse(x.id)) + 1).ToString(),
                is_finish = false,
                title = request.title,
                update_time = DateTime.UtcNow,
            };
            _store.Add(candidate);
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

            var target = _store.SingleOrDefault(x => x.id == id);
            if (target != null)
            {
                target.is_finish = request.is_finish;
                target.title = request.title;
            }
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

            var target = _store.SingleOrDefault(x => x.id == id);
            if (target != null)
            {
                _store.Remove(target);
            }
            return Ok();
        }
    }
}
