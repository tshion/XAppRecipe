using Microsoft.AspNetCore.Mvc;
using Swashbuckle.AspNetCore.Annotations;

namespace WebServer.Controllers.ToDoTasks
{
    /// <summary>
    /// ToDo 関連を取り扱うController
    /// </summary>
    [ApiController]
    [Route("v1/todo")]
    public class ToDoTaskController : ControllerBase
    {
        // TODO: HTTP 要求毎にController 生成されるので、Model 側に持っていきたい
        private readonly List<ToDoEntity> _store = new()
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
        [SwaggerResponse(StatusCodes.Status200OK, "成功", typeof(GetToDoResponse))]
        public IActionResult Get()
        {
            return Ok(new GetToDoResponse()
            {
                items = _store.ToList(),
            });
        }

        /// <summary>
        /// ToDo 新規登録
        /// </summary>
        [HttpPost]
        public void Post([FromBody] PostToDoRequest request)
        {
            var title = request.title;
            if (string.IsNullOrWhiteSpace(title))
            {
                return;
            }

            var candidate = new ToDoEntity
            {
                id = (_store.Max(x => int.Parse(x.id)) + 1).ToString(),
                is_finish = false,
                title = title,
                update_time = DateTime.UtcNow,
            };
            _store.Add(candidate);
        }

        /// <summary>
        /// ToDo 編集
        /// </summary>
        /// <param name="id">識別番号</param>
        [HttpPut("{id}")]
        public void Put(string id, [FromBody] PutToDoRequest request)
        {
            if (string.IsNullOrWhiteSpace(id))
            {
                return;
            }

            var target = _store.SingleOrDefault(x => x.id == id);
            if (target != null)
            {
                target.is_finish = request.is_finish;
                target.title = request.title;
            }
        }

        /// <summary>
        /// ToDo 削除
        /// </summary>
        /// <param name="id">識別番号</param>
        [HttpDelete("{id}")]
        public void Delete(string id)
        {
            if (string.IsNullOrWhiteSpace(id))
            {
                return;
            }

            var target = _store.SingleOrDefault(x => x.id == id);
            if (target != null)
            {
                _store.Remove(target);
            }
        }
    }
}
