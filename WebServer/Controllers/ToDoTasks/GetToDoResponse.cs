namespace WebServer.Controllers.ToDoTasks
{
    public class GetToDoResponse
    {
        public IEnumerable<ToDoEntity> items { get; set; }
    }
}
