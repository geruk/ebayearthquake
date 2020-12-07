package me.geruk.earthquakeviewer;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.List;

import me.geruk.earthquakeviewer.controller.EarthquakeListController;
import me.geruk.earthquakeviewer.model.Earthquake;
import me.geruk.earthquakeviewer.model.EarthquakeViewModel;
import me.geruk.earthquakeviewer.util.TimeFormatterUtil;

public class EarthquakeListFragment extends Fragment {

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView latlng;
        private final TextView time;
        private final TextView magnitude;

        public ViewHolder(View view) {
            super(view);
            latlng = view.findViewById(R.id.row_title);
            time = view.findViewById(R.id.row_subtitle);
            magnitude = view.findViewById(R.id.magnitude);
        }
    }

    public class EarthquakeListAdapter extends RecyclerView.Adapter<ViewHolder> {

        private @Nullable List<Earthquake> localEarthquakeData;

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
            Earthquake eq = localEarthquakeData.get(position);
            holder.time.setText(eq.getTime().format(
                    DateTimeFormatter.ofPattern(TimeFormatterUtil.TIME_FORMAT)));
            holder.latlng.setText(
                    String.valueOf(eq.getLatitude()) + " " + String.valueOf(eq.getLongitude()));
            holder.magnitude.setText(String.valueOf(eq.getMagnitude()));
            Drawable background = ResourcesCompat.getDrawable(
                    getResources(),
                    eq.getMagnitude() >= 8.0 ? R.drawable.red_circular_textview : R.drawable.yellow_circular_textview,
                    null);
            holder.magnitude.setBackground(background);
        }

        @Override
        public int getItemCount() {
            return localEarthquakeData == null ? 0 : localEarthquakeData.size();
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
        earthquakeListView.addItemDecoration(
                new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
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
