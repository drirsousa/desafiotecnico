package br.com.rosait.desafiotecnico.view

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rosait.desafiotecnico.databinding.ActivityMainBinding
import br.com.rosait.desafiotecnico.R
import br.com.rosait.desafiotecnico.model.Cash
import br.com.rosait.desafiotecnico.model.Product
import br.com.rosait.desafiotecnico.model.Spotlight
import br.com.rosait.desafiotecnico.viewmodel.ViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    val viewModel by lazy { ViewModelProvider(this).get(ViewModel::class.java) }
    val spotlightList = arrayListOf<Spotlight>()
    val spotlightAdapter by lazy { SpotlightAdapter(spotlightList) }
    val productList = arrayListOf<Product>()
    val productAdapter by lazy { ProductAdapter(productList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()

        initRecyclerViews()

        viewModel.getResults().observe(this, Observer { result ->
            result.spotlight?.let {
                setSpotlight(it)
            }
            
            result.cash?.let {
                setCash(it)
            }

            result.products?.let {
                setProduct(it)
            }
        })
    }

    private fun initRecyclerViews() {
        mBinding.rvSpotlight.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        mBinding.rvSpotlight.adapter = spotlightAdapter

        mBinding.rvProducts.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        mBinding.rvProducts.adapter = productAdapter
    }

    private fun setSpotlight(spotlightList: ArrayList<Spotlight>) {
        spotlightAdapter.updateList(spotlightList)
    }

    private fun setProduct(productList: ArrayList<Product>) {
        productAdapter.updateList(productList)
    }

    private fun setCash(cash: Cash) {
        cash.bannerURL?.let {
            Picasso.get()
                .load(it)
                .into(mBinding.image)
        }
        cash.title?.let {
            val formattedText = setStyle(it)
            mBinding.txtCash.setText(formattedText)
        }
    }

    private fun setStyle(text: String): SpannableStringBuilder {
        val digioText = "digio"
        val wholeText = SpannableStringBuilder(text)

        setBoldAndColor(wholeText,
            this.resources.getColor(R.color.navy),
            wholeText.indexOf(digioText), wholeText.indexOf(digioText) + digioText.length)

        return wholeText
    }

    private fun setBoldAndColor(spannableStringBuilder: SpannableStringBuilder, color: Int, start: Int, end: Int) {
        setSpan(spannableStringBuilder,
            StyleSpan(Typeface.BOLD) as Any,
            start, end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        setSpan(spannableStringBuilder,
            ForegroundColorSpan(color) as Any,
            start, end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    private fun setSpan(spannableStringBuilder: SpannableStringBuilder, what: Any, start: Int, end: Int, flags: Int) {
        spannableStringBuilder.setSpan(
            what,
            start, end,
            flags)
    }
}