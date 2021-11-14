package science.involta.lesson_12

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.net.URL

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById(R.id.image_view_id)
        val urlView: TextView = findViewById(R.id.url_view)
        val inputUrl: EditText = findViewById(R.id.input_url01)
        val downloadButton: Button = findViewById(R.id.download_button)
        val url: Editable? = inputUrl.text
        val url1: CharSequence = inputUrl.toString()

        downloadButton.setOnClickListener {

            Thread {
//                Patterns.WEB_URL.matcher(url1).matches()

                val bitmap: Bitmap = loadImageFromNetwork("$url")

                imageView.post { imageView.setImageBitmap(bitmap) }
            }.start()
            urlView.text = "Введен URL: ${inputUrl.text}"
        }


    }

    private fun loadImageFromNetwork(link: String): Bitmap{
        val connection = URL(link).openConnection()
        val inputStream = connection.getInputStream()
        val image = BitmapFactory.decodeStream(inputStream)
        return  image
    }
}