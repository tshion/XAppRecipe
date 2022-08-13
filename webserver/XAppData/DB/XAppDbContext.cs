using Microsoft.EntityFrameworkCore;
using System.Runtime.CompilerServices;
using XAppData.DB.Entities;

[assembly: InternalsVisibleTo("XAppApi")]

namespace XAppData.DB
{
    internal class XAppDbContext : DbContext
    {
        internal DbSet<ToDoTaskDbEntity> ToDoTasks => Set<ToDoTaskDbEntity>();



        public XAppDbContext(
            DbContextOptions<XAppDbContext> options
        ) : base(options)
        {
        }
    }
}
