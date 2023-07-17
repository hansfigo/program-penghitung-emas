# program-penghitung-emas
Terminal based program untuk menghitung harga jual dan beli emas untuk final project dengan bahasa kotlin

# Run 
1. Installasi Kotlin Compiler
   <br>
   Pastikan Anda telah menginstal Kotlin Compiler di sistem Anda. Anda dapat mengunduhnya dari situs resmi Kotlin: [https://kotlinlang.org/docs/tutorials/command-line.html](https://kotlinlang.org/docs/command-line.html)
3. Compile code
  ```
  kotlinc Main.kt -include-runtime -d Main.jar
  ```
4. Run Program
 ```
java -jar Main.jar <aksi(jual/beli)> <grams> <punya NPWP?(y/n)>
  ```
Gantilah <aksi(jual/beli)>, <grams>, dan <punya NPWP?(y/n)> dengan argumen yang sesuai untuk pengujian. Contoh:
```
java -jar Main.jar jual 50 y
```
