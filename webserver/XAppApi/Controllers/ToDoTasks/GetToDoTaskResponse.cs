using Microsoft.OpenApi.Any;
using Microsoft.OpenApi.Models;
using Swashbuckle.AspNetCore.Annotations;
using Swashbuckle.AspNetCore.SwaggerGen;

namespace XAppApi.Controllers.ToDoTasks
{
    [SwaggerSchemaFilter(typeof(GetToDoTaskResponseFilter))]
    public class GetToDoTaskResponse
    {
        [SwaggerSchema(Nullable = false)]
        public IEnumerable<ToDoTask> items { get; set; }
    }


    public class GetToDoTaskResponseFilter : ISchemaFilter
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
