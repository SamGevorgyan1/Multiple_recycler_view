package com.multiplerecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.multiplerecyclerview.adapter.PostAdapter
import com.multiplerecyclerview.model.PostDataModel


class MainActivity : AppCompatActivity(), (PostDataModel.WebViewPost) -> Unit {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rec)
        val postAdapter = PostAdapter(this)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter

        }
        postAdapter.updateData(addList())
    }

    private fun addList(): MutableList<PostDataModel> = mutableListOf(
        PostDataModel.ImagePost(
            imagePostUrl = "https://images.unsplash.com/photo-1509043759401-136742328bb3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80"
        ),
        PostDataModel.ImagePost(
            imagePostUrl = "https://allthatsinteresting.com/wordpress/wp-content/uploads/2013/09/interesting-pictures-of-clouds.jpg"
        ),
        PostDataModel.TextPost(
            textPost = "Pakistan’s foreign minister says that the US ambassador to the country has been summoned after President Joe Biden in a speech said Pakistan “may be one of the most dangerous” countries in the world which had “nuclear weapons without any cohesion”.\n" +
                    "\n" +
                    "The 79-year-old Biden made the comments during a reception of the Democratic Congressional Campaign Committee on Thursday in which he also touched upon the war in Ukraine, China and local domestic issues."
        ),
        PostDataModel.ImagePost(
            imagePostUrl = "https://media.istockphoto.com/photos/businessman-standing-on-the-top-of-rock-picture-id1366352000?b=1&k=20&m=1366352000&s=170667a&w=0&h=Jq1D4K4I0yDJ09kq8Q5fVl40YnstFd_RMI4Pyou0v3E="
        ),
        PostDataModel.TextPost(
            textPost = "Iranian security official confirms fire at Evin prison, says situation is under control after social media footage emerges"
        ),

        PostDataModel.ImagePost(
            imagePostUrl = "https://static.toiimg.com/thumb/msid-87137761,width-748,height-499,resizemode=4,imgsize-101646/.jpg"
        ),
        PostDataModel.TextPost(
            textPost = "At least 20 people died and 15 others were injured when a bus overturned on the Pan-American Highway in southwestern Colombia.\n" +
                    "\n" +
                    "The bus was travelling between the port city of Tumaco, in the southwestern corner of Colombia, and Cali, 320km (200 miles) to the northeast, when the incident occurred on Saturday."
        ),

        PostDataModel.WebViewPost(
            webImgUrl = "https://s.rbk.ru/v1_companies_s3/resized/960xH/media/trademarks/2d456d51-b595-41ec-9f42-32d281a106e3.jpg",
            webViewUrl = "https://kinogo.biz/"
        )

    )

    override fun invoke(webViewUrl: PostDataModel.WebViewPost) {

      intentWebView(webViewUrl)

    }
    private fun intentWebView(post:PostDataModel.WebViewPost){
        val intent=Intent(this@MainActivity,WebViewActivity::class.java)
        intent.putExtra("web",post.webViewUrl)
        startActivity(intent)
    }
}

