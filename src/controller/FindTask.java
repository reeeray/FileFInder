package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.io.File;

/**
 * Created by User: Shein G.A.
 */

public class FindTask extends Task<Void> {
    final private int SIZE = 200;
    final private ObservableList<String> items;
    final private String path;
    final private String end;

    final ObservableList<String> list = FXCollections.observableArrayList();


    public FindTask(ObservableList<String> items, String path, String end) {
        this.items = items;
        this.path = path;
        this.end = end;
//        list.addListener(new ListChangeListener<String>() {
//            @Override
//            public void onChanged(Change<? extends String> c) {
//                while(c.next()) {
//                    if(c.wasAdded()) {
//                        c.getAddedSubList().forEach(element -> items.add(element));
//                    }
//                }
//            }
//        });
    }

    @Override
    protected Void call() throws Exception {
        final File file = new File(path);
        if (file.isDirectory()) {
            findIn(file);
        }
        return null;
    }

    @Override
    protected void cancelled() {
        items.add("canceled by user....");
        updateScene();

    }

    @Override
    protected void succeeded() {
        //items.addAll(list);
        items.add("finded" + items.size()+ " files");
        updateScene();

    }

    private void findIn(File fi) {
        File[] files=fi.listFiles((f)->f.getName().endsWith(end) || f.isDirectory());

        if (isCancelled()) {
            return;
        }

        if (files!=null && items.size()<SIZE) {
            for(File f : files)
                if (f.isDirectory()) {
                    findIn(f);
                }else {

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            items.add(f.getAbsolutePath());//кидает в очередь и в потоке JavaFX он её разбирает
                        }
                    });
                }
        }
        updateProgress(items.size(), SIZE);
    }

    void updateScene(){
        updateProgress(SIZE, SIZE);
    }
}

