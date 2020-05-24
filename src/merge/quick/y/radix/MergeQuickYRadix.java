package merge.quick.y.radix;

import com.sun.org.apache.bcel.internal.classfile.ConstantMethodref;
import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Arrays;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MergeQuickYRadix {

    public static int contador = 0;
    public static int c = 0;

    public static int ordenRadix(int n, int b[]) {

        int contador = 0;
        int mx = b[0];
        //contador+=4;
        for (int i = 1; i < n; i++) //Indica cual es el numero mayor
        {   //contador+=4;
            if (b[i] > mx) {
                //contador+=4;
                mx = b[i];
            }
        }
        contador += 3;
        for (int exp = 1; mx / exp > 0; exp *= 10) //Dependiendo el numero mayor llama a los exponentes necesarios para ordenar
        {
            contador += 4;
            int exterior[] = new int[n]; // Guarda las matrices en la posición exterior
            int i;
            int actual[] = new int[n]; //Guarda las matrices en ejecución
            Arrays.fill(actual, 0);
            contador += 4;
            //System.out.println("1.El arreglo ordenado por radix es: "+Arrays.toString(b) );

            for (i = 0; i < n; i++) //
            {
                contador += 6;
                actual[(b[i] / exp) % 10]++;
            }

            contador += 2;
            for (i = 1; i < n; i++) //Cambia la posición para que ahora contenga el digito del exponente de salida
            {
                contador += 6;
                actual[i] += actual[i - 1];
            }
            contador += 3;
            for (i = n - 1; i >= 0; i--) {
                contador += 8;
                exterior[actual[(b[i] / exp) % 10] - 1] = b[i];
                contador += 4;
                actual[(b[i] / exp) % 10]--;
            }

            contador += 2;
            for (i = 0; i < n; i++) //Guarda la matriz, de tal modo que ahora esten ordenados segun el digito
            {
                contador += 5;
                b[i] = exterior[i];
            }
        }
        return contador;
    }

    public static int quickSort(int i, int j, int a[]) {
        contador += 4;
        int tempi = i;
        int tempj = j;
        int x;
        x = a[(i + j) / 2];
        do {

            while (a[i] < x) {
                contador += 3;
                i++;
            }
            while (x < a[j]) {
                j--;
                contador += 3;
            }
            contador++;
            if (i < j) {
                contador += 5;
                cambio(a, i, j);
                i = i + 1;
                j = j - 1;
            }
            contador++;
        } while (i < j);
        contador += 6;
        if (i == j) {
            contador += 2;
            if (x < a[i]) {
                j = j - 1;
                contador += 2;
            } else {
                contador += 2;
                i = i + 1;
            }
        }
        if (j - 1 == tempi) {
            contador += 3;
            if (a[tempi] > a[j]) {
                contador++;
                cambio(a, tempi, j);
            }
        } else {
            contador++;
            if (j > tempi) {
                contador++;
                quickSort(tempi, j, a);
            }
        }
        if (i + 1 == tempj) {
            contador += 3;
            if (a[i] > a[tempj]) {
                cambio(a, i, tempj);
                contador++;
            }
        } else {
            contador++;
            if (i < tempj) {
                contador++;
                quickSort(i, tempj, a);
            }
        }
        return contador;
    }// quicksort

    public static int ordenarMerge(int[] lista) {

        int len = lista.length;
        c += 1;

        c = c + 1;
        if (len < 2) {
            c++;
            return c;
        }

        c = c + 2;
        int mitad = len / 2;

        c = c + 2;
        int[] lista_izq = new int[mitad];

        c = c + 3;
        int[] lista_der = new int[len - mitad];

        c = c + 1;
        int temp = 0;

        c = c + 2;
        for (int i = 0; i < len; ++i) {
            c = c + 2;

            c = c + 1;
            if (i < mitad) {

                c = c + 3;
                lista_izq[i] = lista[i];

            } else {

                c = c + 3;
                lista_der[temp] = lista[i];

                c = c + 2;
                temp = temp + 1;
            }
        }
        c += 2;
        ordenarMerge(lista_izq);
        ordenarMerge(lista_der);

        int i = 0;
        int j = 0;
        int k = 0;
        c = c + 3;

        c = c + 4;
        while (j < mitad && k < len - mitad) {
            c = c + 4;

            c = c + 3;
            if (lista_izq[j] < lista_der[k]) {

                lista[i++] = lista_izq[j++];
                c = c + 5;

            } else {
                lista[i++] = lista_der[k++];
                c = c + 5;
            }
        }

        c = c + 1;
        while (j < mitad) {
            c = c + 1;

            lista[i++] = lista_izq[j++];
            c = c + 5;
        }

        c = c + 2;
        while (k < len - mitad) {
            c = c + 2;

            lista[i++] = lista_der[k++];
            c = c + 5;
        }

        return c;
    }

    public static void cambio(int a[], int i, int j) {
        int t;
        t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static int[] generaArreglo(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = (int) (Math.random() * 99999);

        }
        return a;
    }

    public static void main(String[] args) {

        XYSeries radix = new XYSeries("Radix");

        for (int i = 11; i <= 1511; i+=20) {
            int a[] = generaArreglo(i);
            radix.add(i, ordenRadix(i, a));
            
        }

        XYSeries quick = new XYSeries("Quick");

        for (int i = 1; i <= 1501; i+=20) {
            int a[] = generaArreglo(i);
            quick.add(i, quickSort(0, a.length - 1, a));
            contador = 0;
            
        }

        XYSeries merge = new XYSeries("Merge");

        for (int i = 1; i <= 1501; i+=20) {
            int a[] = generaArreglo(i);
            merge.add(i, ordenarMerge(a));
            c = 0;
            
        }

        XYSeriesCollection dataSet = new XYSeriesCollection();

        dataSet.addSeries(radix);
        dataSet.addSeries(quick);
        dataSet.addSeries(merge);

        JFreeChart xyLineChart = ChartFactory.createXYLineChart("Comparación entre métodos de ordenamiento", "Tamaño", "Operaciones elementales", dataSet, PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = xyLineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.ORANGE);
        renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        renderer.setSeriesStroke(1, new BasicStroke(1.0f));
        renderer.setSeriesStroke(2, new BasicStroke(1.0f));
        plot.setRenderer(renderer);

        ChartPanel panel = new ChartPanel(xyLineChart);

        JFrame ventana = new JFrame();
        ventana.setVisible(true);
        ventana.setSize(1300, 700);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ventana.add(panel);

        //******************************
    }

}
