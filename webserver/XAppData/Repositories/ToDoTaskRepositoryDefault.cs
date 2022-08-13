using Microsoft.EntityFrameworkCore;
using XApp.Entities;
using XApp.Repositories;
using XAppData.DB;

namespace XAppData.Repositories
{
    internal class ToDoTaskRepositoryDefault : ToDoTaskRepository
    {
        private readonly XAppDbContext _dbXApp;



        public ToDoTaskRepositoryDefault(
            XAppDbContext dbXApp
        )
        {
            _dbXApp = dbXApp;
        }



        public void Delete(ToDoTaskEntity target)
        {
            var candidate = _dbXApp.ToDoTasks.Find(target.Id.ToString());
            if (candidate is not null)
            {
                _dbXApp.ToDoTasks.Remove(candidate);
                _dbXApp.SaveChangesAsync();
            }
        }

        public IEnumerable<ToDoTaskEntity> Load()
           => _dbXApp.ToDoTasks
            .Select(item => new ToDoTaskEntity(
                Ulid.Parse(item.id),
                item.finished,
                item.title,
                item.update_date
            ))
            .AsNoTracking()
            .ToList();

        public void Save(ToDoTaskEntity value)
        {
            var target = _dbXApp.ToDoTasks.Find(value.Id.ToString());
            if (target is null)
            {
                target = new DB.Entities.ToDoTaskDbEntity
                {
                    id = value.Id.ToString(),
                    finished = value.IsFinished,
                    title = value.Title,
                    update_date = value.UpdateDate
                };
                _dbXApp.ToDoTasks.Add(target);
            }
            else
            {
                target.finished = value.IsFinished;
                target.title = value.Title;
                target.update_date = value.UpdateDate;
            }
            _dbXApp.SaveChanges();
        }
    }
}
