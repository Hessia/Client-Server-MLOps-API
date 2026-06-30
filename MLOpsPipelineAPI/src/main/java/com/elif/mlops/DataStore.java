package com.elif.mlops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore {

    public static Map<String, MLWorkspace> workspaces = new HashMap<>();

    public static Map<String, MachineLearningModel> models = new HashMap<>();

    public static Map<String, List<EvaluationMetric>> metrics = new HashMap<>();

}