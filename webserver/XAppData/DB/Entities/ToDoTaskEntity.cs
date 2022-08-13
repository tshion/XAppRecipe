using System.ComponentModel.DataAnnotations.Schema;

namespace XAppData.DB.Entities
{
    [Table("todo_tasks")]
    internal class ToDoTaskDbEntity
    {
        internal string id { get; set; }

        internal bool finished { get; set; }

        internal string title { get; set; }

        internal DateTime update_date { get; set; }
    }
}
