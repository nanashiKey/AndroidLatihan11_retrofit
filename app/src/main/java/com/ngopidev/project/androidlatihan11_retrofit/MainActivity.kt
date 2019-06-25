package com.ngopidev.project.androidlatihan11_retrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.e
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var disposable : Disposable? = null
    private val wikiApiServices by lazy{
        WikiApiServices.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener {
            if(edit_search.text.toString().isNotEmpty()){
                beginSearch(edit_search.text.toString())
            }else{
                Toast.makeText(this, "inputan tidak boleh kosong",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun beginSearch(search : String){
        disposable = wikiApiServices.hitCountCheck("query", "json"
            ,"search", search).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    result -> txt_search_result.text =
                    "${result.query.searchInfo.totahits} berhasil ditemukan"
                },
                {
                    error -> Toast.makeText(this, error.message,
                    Toast.LENGTH_LONG).show()
                    e("TAG_ERROR", error.message)
                }
            )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

}
