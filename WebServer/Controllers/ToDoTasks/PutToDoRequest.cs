using Microsoft.OpenApi.Any;
using Microsoft.OpenApi.Models;
using Swashbuckle.AspNetCore.Annotations;
using Swashbuckle.AspNetCore.SwaggerGen;
using System.ComponentModel.DataAnnotations;

namespace WebServer.Controllers.ToDoTasks
{
    [SwaggerSchemaFilter(typeof(PutToDoRequestFilter))]
    public class PutToDoRequest
    {
        [SwaggerSchema("完了フラグ")]
        public bool is_finish { get; set; }

        [Required(AllowEmptyStrings = false)]
        [SwaggerSchema("やること名")]
        public string title { get; set; }
    }


    public class PutToDoRequestFilter : ISchemaFilter
    {
        public void Apply(OpenApiSchema schema, SchemaFilterContext context)
        {
            schema.Example = new OpenApiObject
            {
                ["is_finish"] = new OpenApiBoolean(false),
                ["title"] = new OpenApiString("やること名"),
            };
        }
    }
}
