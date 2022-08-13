using Microsoft.EntityFrameworkCore;
using XAppData.DB.Entities;

namespace XAppData.DB
{
    internal class XAppDbContext : DbContext
    {
        internal DbSet<ToDoTaskDbEntity> ToDoTasks => Set<ToDoTaskDbEntity>();



        internal XAppDbContext(
            DbContextOptions<XAppDbContext> options
        ) : base(options)
        {
        }
    }
}
