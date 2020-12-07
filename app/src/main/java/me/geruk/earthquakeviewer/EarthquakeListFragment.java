package me.geruk.earthquakeviewer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.geruk.earthquakeviewer.controller.EarthquakeListController;
import me.geruk.earthquakeviewer.model.Earthquake;
import me.geruk.earthquakeviewer.model.EarthquakeViewModel;

public class EarthquakeListFragment extends Fragment {

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView time;

        public ViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.row_title);
        }

        public TextView getTime() {
            return time;
        }
    }

    public class EarthquakeListAdapter extends RecyclerView.Adapter<EarthquakeListFragment.ViewHolder> {


        private List<Earthquake> localEarthquakeData;

        // TODO: perf - diff the dataset
        public void setLocalEarthquakeData(List<Earthquake> localEarthquakeData) {
            this.localEarthquakeData = localEarthquakeData;
            this.notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.earthquake_list_row, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.getTime().setText(localEarthquakeData.get(position).getTime().toString());
        }

        @Override
        public int getItemCount() {
            return localEarthquakeData.size();
        }
    }

    RecyclerView earthquakeListView;
    EarthquakeListAdapter earthquakeListAdapter;

    public EarthquakeListFragment() {
        super(R.layout.list_fragment);
    }

    @Override
    public void onCreate(Bundle onSavedInstance) {
        super.onCreate(onSavedInstance);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        earthquakeListView = (RecyclerView) view;
        earthquakeListAdapter = new EarthquakeListAdapter();
        earthquakeListView.setLayoutManager(new LinearLayoutManager(getContext()));
        earthquakeListView.setAdapter(earthquakeListAdapter);
        new ViewModelProvider(requireActivity())
                .get(EarthquakeViewModel.class)
                .getEarthquakes()
                .observe(getViewLifecycleOwner(), new Observer<List<Earthquake>>() {
            @Override
            public void onChanged(List<Earthquake> list) {
                earthquakeListAdapter.setLocalEarthquakeData(list);
            }
        });
    }
}
