package androidy.chandora.ankit.quizy.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

import androidy.chandora.ankit.quizy.Pojo.Category;
import androidy.chandora.ankit.quizy.R;

/**
 * Created by chandora on 15/3/18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    ArrayList<Category> categories;

    public CategoryAdapter(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.categoryName.setText(categories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        CheckBox hardCheckbox, mediumCheckbox, easyCheckbox;
        Button playButton;
        FoldingCell fc;

        public MyViewHolder(View view) {
            super(view);

            categoryName = view.findViewById(R.id.category_name_folded);
            fc = view.findViewById(R.id.folding_cell);
            playButton = view.findViewById(R.id.play_btn);
            hardCheckbox = view.findViewById(R.id.checkbox_hard);
            mediumCheckbox = view.findViewById(R.id.checkbox_medium);
            easyCheckbox = view.findViewById(R.id.checkbox_easy);


            hardCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkboxSelection(hardCheckbox,easyCheckbox,mediumCheckbox);
                }
            });

            mediumCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkboxSelection(mediumCheckbox,easyCheckbox,hardCheckbox);
                }
            });

            easyCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkboxSelection(easyCheckbox,hardCheckbox,mediumCheckbox);
                }
            });


            fc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fc.toggle(false);
                }
            });
        }

        public void checkboxSelection(CheckBox hard, CheckBox medium, CheckBox easy) {
            if (hard.isChecked()) {
                hard.setChecked(true);
                medium.setChecked(false);
                easy.setChecked(false);

            } else if (!easy.isChecked() && !medium.isChecked()) {
                hard.setChecked(true);
            } else {
                hard.setChecked(false);
            }

        }

    }
}
