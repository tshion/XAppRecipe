using Microsoft.OpenApi.Models;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(c =>
{
    c.SwaggerDoc("preview", new()
    {
        Title = "XAppRecipe 用WebAPI",
        Description = @"
## UML
* [ToDo関連フロー](https://www.plantuml.com/plantuml/png/rLPjQzDG5Fv-VyNz0slnQw4oOKK4qU8w_2ZfShTTw9gIt1P5X2QHwTvme7NgcBewQrVPG9mMLdK_vYnDyoyyzqQRjFMb86ZDXv1ppdDod-Syfo_Jbj1CKLukq9mnOmIFwq7r5RWrS0_1_KG45mMcAdIuHBHvOPWKt0P6u7p4EzKiMhQOIVB6JGfE7TnjS5k4ubN2G_WyBsb5GQzeWbVOt2K5voFbhCaiLXJCbFNIzs04TesL38iZXLiIjspeh322cSMojiWaGCyJLjGfCYDnn7MMlv1LJBGI9uH8-dJgFFAYAUe_wWIxzTvUqzy_wJNixZj7YkOLJJ197368x3CSQl1tZhtLpQWXKX9rO7j8l8yAGtMNLKcgNvD1qS07aoyk2Meig8pKV36JeydAkQGmT4F9b32II2G88kXK_nYT3Rpisp5n4N58s1DZg-OIHqHjvV0OvNuCW715Ot2ktltN_UteVMXxwpMmMs0VZ3NTOFTvzxZJhRxnlcpyThhnzg3wxEnqpxlNyBTMm3u21zSOz_azE1rmt_wvJf9Ezqu_Usilq-b1kM7s1miqQAPw9ve9pbrmri5-27RJszgC_1oQoDzPhUpLsTXkgPJwKaZzdEU4x1twuRURmSxAi1zXTcm_g7UqSNQ2AxCDzWkmKV41s1Ykg-A4kJKNcPMypVKxeMDSnoqyV8AVEx_MyXwqmwHrOu4NkRMK-hN2_yTkRtKjUBe_R7UO7TzkzMitCAr_w-bCvd8cbndBredpP1ePuN-Bhm00)
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
