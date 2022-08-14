namespace XApp.Types
{
    /// <summary>
    /// テキスト形式のID
    /// </summary>
    public sealed class IdTextType
    {
        public static IdTextType NewId()
        {
            var candidate = Ulid.NewUlid();
            return new IdTextType() { Token = candidate.ToString() };
        }

        public static IdTextType Parse(string value)
        {
            var candidate = Ulid.Parse(value);
            return new IdTextType() { Token = candidate.ToString() };
        }

        public static bool TryParse(string value, out IdTextType? result)
        {
            var isParsed = Ulid.TryParse(value, out var tmp);
            result = isParsed ? new IdTextType() { Token = tmp.ToString() } : null;
            return isParsed;
        }



        private IdTextType() { }



        public string Token { get; private init; }
    }
}
