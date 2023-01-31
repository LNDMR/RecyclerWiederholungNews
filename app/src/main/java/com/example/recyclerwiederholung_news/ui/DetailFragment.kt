package com.example.recyclerwiederholung_news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.recyclerwiederholung_news.MainViewModel
import com.example.recyclerwiederholung_news.databinding.FreagmentDetailBinding

class DetailFragment: Fragment() {

    private lateinit var binding: FreagmentDetailBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FreagmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val newsId = requireArguments().getInt("newsId")

        viewModel.news.observe(viewLifecycleOwner) { list ->
            val article = list.find { it.id == newsId }

            if (article != null) {
                binding.detailImage.setImageResource(article.imageResourceId)
                binding.detailTitelText.text = article.title
                binding.detailDateText.text = article.date
                binding.detailArticleText.text = article.article
            }
        }
    }
}