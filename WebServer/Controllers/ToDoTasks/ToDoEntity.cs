namespace WebServer.Controllers.ToDoTasks
{
    public class ToDoEntity
    {
        /// <summary>
        /// 識別番号
        /// </summary>
        public string id { get; set; }

        /// <summary>
        /// 完了フラグ
        /// </summary>
        public bool is_finish { get; set; }

        /// <summary>
        /// ToDo タイトル
        /// </summary>
        public string title { get; set; }

        /// <summary>
        /// 更新日時
        /// </summary>
        public DateTime update_time { get; set; }
    }
}
