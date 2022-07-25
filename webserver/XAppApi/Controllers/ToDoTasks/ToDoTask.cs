using Swashbuckle.AspNetCore.Annotations;
using System.ComponentModel.DataAnnotations;

namespace XAppApi.Controllers.ToDoTasks
{
    public class ToDoTask
    {
        [MinLength(1)]
        [SwaggerSchema("識別番号", Nullable = false)]
        public string id { get; set; }

        [SwaggerSchema("完了フラグ")]
        public bool is_finish { get; set; }

        [MinLength(1)]
        [SwaggerSchema("ToDo タイトル", Nullable = false)]
        public string title { get; set; }

        [SwaggerSchema("更新日時", Format = "date-time")]
        public DateTime update_time { get; set; }
    }
}
