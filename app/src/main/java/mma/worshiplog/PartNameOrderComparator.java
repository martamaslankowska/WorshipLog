package mma.worshiplog;

import java.util.Comparator;

import mma.worshiplog.model.LogDetail;

public class PartNameOrderComparator implements Comparator<LogDetail> {
    @Override
    public int compare(LogDetail x, LogDetail y) {
        return compare(x.getPartOrder(), y.getPartOrder());
    }

    private static int compare(int a, int b) {
        return Integer.compare(a, b);
    }
}

