package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Serializer implements Serializable {
        public ArrayList<Task> toDO;
        public ArrayList<Task> inProgress;
        public ArrayList<Task> done;
}
