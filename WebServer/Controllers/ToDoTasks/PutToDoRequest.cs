namespace WebServer.Controllers.ToDoTasks
{
    public class PutToDoRequest
    {
        /// <summary>
        /// 完了フラグ
        /// </summary>
        public bool is_finish { get; set; }

        /// <summary>
        /// やること名
        /// </summary>
        public string title { get; set; }
    }
}
