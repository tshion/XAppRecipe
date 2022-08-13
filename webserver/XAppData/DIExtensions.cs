using Microsoft.Extensions.DependencyInjection;
using XApp.Repositories;
using XAppData.DB;
using XAppData.Repositories;

namespace XAppData
{
    public static class DIExtensions
    {
        /// <summary>
        /// XApp のデータ操作関連の登録
        /// </summary>
        /// <param name="sqliteFileName">SQLite のファイル名</param>
        public static IServiceCollection AddXAppServices(
            this IServiceCollection services,
            string sqliteFileName
        )
        {
            services.AddSqlite<XAppDbContext>(
                $"Data Source=${sqliteFileName}",
                builder => builder.MigrationsAssembly("XAppApi")
            );

            services.AddScoped<ToDoTaskRepository, ToDoTaskRepositoryDefault>();

            return services;
        }
    }
}
