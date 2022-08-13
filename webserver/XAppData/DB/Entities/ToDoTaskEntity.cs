using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace XAppData.DB.Entities
{
    [Table("todo_tasks")]
    internal class ToDoTaskDbEntity
    {
        [Key]
        public string id { get; set; }

        public bool finished { get; set; }

        public string title { get; set; }

        public DateTime update_date { get; set; }
    }
}
