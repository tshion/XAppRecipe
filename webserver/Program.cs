using Microsoft.OpenApi.Models;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(c =>
{
    c.EnableAnnotations();
    c.SwaggerDoc("preview", new()
    {
        Title = "XAppRecipe 用WebAPI",
        Description = @"
## UML
* [ToDo関連フロー](https://www.plantuml.com/plantuml/png/vLPjQzDG5Fv-VyNz0slnww0oOKK4qU8w_2ZfShTTw9gIf4KHeKbaRzrOGRUfSshhh5t7fiDfONNhZxbDa_mBpxtH9cqd5WNfD1z2xZdFoNsUyvmRCg7faghd5j8ugKmgtbR5AxnbrYQp3fdr5UbKJnEHmVqf9CteYegPLOKLCx_07KiQpcb4HIdbFcPcXLdRpAeZ35SMDg4pD2jbT7nBqccUJDyGS3gGJgX48ncTg3oV_R4OWDqXMKMZGE41noqeCaaFOAOWBSqH3f1JY6Ha3CnG676NfAub11CfIn52d3uUkmgyy3XsdZQztOfRhZbxfsxLw1oRN3JDIphXn043o2x3lW9dvyHUMGyA0YL11PJxnBieVodkFCj9TNDyaL7WGQLpypfMPaM4QxuUJ-9e_afKLsH5oEGm58b4423mM7SRcVIyxFisOH5XY5yJOYlw4aO4PJb_6-5-201mGQEth_Rok-vqk9yRTd6J6NLcx0_LNM_tLUUasIcyjy_NVjdTSBbNUD5kbUsbgbDQPCOHCs6COPu_CRF9h8C_rubYiSxMHxUwqJvxsLaknMAzejtMcRtwve8nwhLKl1aiPUPZPXQPyOGPDRkq7hZQrvU_Cs29srEX2HCXyMaO_pdF4Pa-SCLfrBoTnNu__EZGVc3xmxbxN6RMalZSjKHin4oQ3ZoAFgJo8zyeAiFm7Jw3G-TirksDXX_KxitI3DNcuJGUhRLFuKHiC-CrCq3DFZDWMRnaBlio-bssLrQzvtk_x_B_wUZ5R2RZD-F9-524Hd0-t8CgCmtG_6-Do6KsPG8OmP_dDm00)
        ",
        Version = "0.0.1",
    });
});

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger(c =>
    {
        c.PreSerializeFilters.Add((swagger, httpReq) =>
        {
            swagger.Servers = new List<OpenApiServer> { new() { Url = $"{httpReq.Scheme}://{httpReq.Host.Value}", Description = "モックサーバーへの接続", } };
        });
    });
    app.UseSwaggerUI(c =>
    {
        c.SwaggerEndpoint("preview/swagger.json", "XAppRecipe 用WebAPI(プレビュー)");
    });
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
