using XApp.Entities;

namespace XApp.Repositories
{
    /// <summary>
    /// やること関連のデータ操作
    /// </summary>
    public interface ToDoTaskRepository
    {
        /// <summary>
        /// 削除
        /// </summary>
        /// <param name="target">対象データ</param>
        public void Delete(ToDoTaskEntity target);

        /// <summary>
        /// 読み込み
        /// </summary>
        public IEnumerable<ToDoTaskEntity> Load();

        /// <summary>
        /// 保存
        /// </summary>
        /// <param name="value">保存データ</param>
        public void Save(ToDoTaskEntity value);
    }
}
