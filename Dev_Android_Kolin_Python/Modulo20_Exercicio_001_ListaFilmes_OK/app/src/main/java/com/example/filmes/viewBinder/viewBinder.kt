package com.example.filmes.viewBinder

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

/** Adaptador para visualização da imagem. (Configurações da Imagem) */
@BindingAdapter("srcUrl")
fun ImageView.bindScrUrl(
    url: String
){

    Glide                                 //Carregamento de Imagem
        .with(this)                 //Local aonde está carregando a imagem (THIS) fragemento que está sendo executada
        .load(url)                       //Variavel url informa o endereço da imagem
        .centerInside()                  //imagem centralizada mantendo sua proporção
        .into(this)                 //local aonde a imagem será exibida (this) no local que executou o Adaptador

}


@BindingAdapter("imageList")
fun ImageCarousel.imageList(imageList: List<CarouselItem>?) {
    imageList?.let {
        this.setData(it)
    }
}