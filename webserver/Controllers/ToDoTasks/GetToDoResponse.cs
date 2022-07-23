using Microsoft.OpenApi.Any;
using Microsoft.OpenApi.Models;
using Swashbuckle.AspNetCore.Annotations;
using Swashbuckle.AspNetCore.SwaggerGen;

namespace WebServer.Controllers.ToDoTasks
{
    [SwaggerSchemaFilter(typeof(GetToDoResponseFilter))]
    public class GetToDoResponse
    {
        [SwaggerSchema(Nullable = false)]
        public IEnumerable<ToDo> items { get; set; }
    }


    public class GetToDoResponseFilter : ISchemaFilter
    {
        public void Apply(OpenApiSchema schema, SchemaFilterContext context)
        {
            schema.Example = new OpenApiObject
            {
                ["items"] = new OpenApiArray
                {
                    new OpenApiObject
                    {
                        ["id"] = new OpenApiString("1"),
                        ["is_finish"] = new OpenApiBoolean(false),
                        ["title"] = new OpenApiString("やることその１"),
                        ["update_date"] = new OpenApiDateTime(DateTime.UtcNow),
                    },
                    new OpenApiObject
                    {
                        ["id"] = new OpenApiString("2"),
                        ["is_finish"] = new OpenApiBoolean(true),
                        ["title"] = new OpenApiString("やることその２"),
                        ["update_date"] = new OpenApiDateTime(DateTime.UtcNow),
                    },
                }
            };
        }
    }
}
