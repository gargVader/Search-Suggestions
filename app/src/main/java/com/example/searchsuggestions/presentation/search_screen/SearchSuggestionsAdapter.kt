package com.example.searchsuggestions.presentation.search_screen

import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchsuggestions.R

class SearchSuggestionsAdapter(
    private val onFillClick: (suggestion: String) -> Unit,
    private val onSearchClick: (suggestion: String) -> Unit
) :
    RecyclerView.Adapter<SearchSuggestionsAdapter.ViewHolder>() {

    var data: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var query: String = ""

    class ViewHolder(
        view: View,
        val onFillClick: (suggestion: String) -> Unit,
        val onSearchClick: (suggestion: String) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.suggestion_text_view)
        val suggestionLayout: LinearLayout = view.findViewById(R.id.suggestion_layout)
        val fillButton: ImageView = view.findViewById(R.id.fill_button)

        init {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_suggestion_item, parent, false)

        return ViewHolder(view, onFillClick, onSearchClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val suggestion = data[position]
        holder.suggestionLayout.setOnClickListener {
            onSearchClick(suggestion)
        }
        holder.fillButton.setOnClickListener {
            onFillClick(suggestion)
        }
        // Build spannable string
        val index = (suggestion.lowercase()).indexOf(query.lowercase())
        val spannableString = SpannableString(suggestion)
        if (index != -1) {
            spannableString.setSpan(StyleSpan(Typeface.BOLD), index, index + query.length, 0)
        }
        holder.textView.text = spannableString
    }

    fun updateQuery(query: String) {
        this.query = query
    }
}