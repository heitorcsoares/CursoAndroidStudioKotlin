package com.example.hqawasomeapp.viewBinder

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

/** Adaptador para visualização da imagem.*/
@BindingAdapter("srcUrl")                   //PARA FAZER O LINK COM AS IMAGENS
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
fun ImageCarousel.imageList(imageList: List<CarouselItem>?){
    imageList?.let {                                                                                 /** Verifica se lista é vazia ou nula    */
        this.setData(it)
    }
}