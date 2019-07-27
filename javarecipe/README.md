# JavaRecipe
Java での実装レシピを整備していくリポジトリ


## 開発言語について
このモジュールはJava 8 を設定しています。
しかし、Android OS レベルによって、Java 8 から追加された標準API(例: Stream) が使えません。
なのでこのモジュールでは[aNNiMON / Lightweight-Stream-API](https://github.com/aNNiMON/Lightweight-Stream-API) を利用し、Stream 等の補完をしています。
import する時に名前空間に注意してください。
