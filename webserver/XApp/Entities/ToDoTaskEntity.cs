namespace XApp.Entities
{
    /// <summary>
    /// やることの内容
    /// </summary>
    /// <param name="Id">識別番号</param>
    /// <param name="IsFinished">完了したかどうか</param>
    /// <param name="Title">やることのタイトル</param>
    /// <param name="UpdateDate">更新日時</param>
    public record class ToDoTaskEntity(
        string Id,
        bool IsFinished,
        string Title,
        DateTime UpdateDate
    );
}
