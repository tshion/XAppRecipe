using Microsoft.OpenApi.Any;
using Microsoft.OpenApi.Models;
using Swashbuckle.AspNetCore.Annotations;
using Swashbuckle.AspNetCore.SwaggerGen;
using System.ComponentModel.DataAnnotations;

namespace WebServer.Controllers.ToDoTasks
{
    [SwaggerSchemaFilter(typeof(PostToDoRequestFilter))]
    public class PostToDoRequest
    {
        [Required(AllowEmptyStrings = false)]
        [SwaggerSchema("やること名")]
        public string title { get; set; }
    }


    public class PostToDoRequestFilter : ISchemaFilter
    {
        public void Apply(OpenApiSchema schema, SchemaFilterContext context)
        {
            schema.Example = new OpenApiObject
            {
                ["title"] = new OpenApiString("やること名"),
            };
        }
    }
}
