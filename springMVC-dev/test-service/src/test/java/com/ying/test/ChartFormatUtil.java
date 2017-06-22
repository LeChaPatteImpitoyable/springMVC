package com.ying.test;

import java.awt.*;

import com.jxcell.*;

public class ChartFormatUtil {

	public static void getExcel(String outPath)
    {

        View m_view = new View();
//        RangeRef newRange = null;
        try {
            m_view.getLock();
            //标题 setTextAsValue(行，列，值)；
            m_view.setTextAsValue(1,2,"Jan");
            m_view.setTextAsValue(1,3,"Feb");
            m_view.setTextAsValue(1,4,"Mar");
            m_view.setTextAsValue(1,5,"Apr");
            //分项
            m_view.setTextAsValue(2,1,"香蕉");
            m_view.setTextAsValue(3,1,"大鸭梨");
            m_view.setTextAsValue(4,1,"芒果");
            m_view.setTextAsValue(5,1,"水果1");
            m_view.setTextAsValue(6,1,"水果2");
            m_view.setTextAsValue(7,1,"共计");
            //
            m_view.setTextAsValue(1,6,"time");
            m_view.setNumber(2,6,1);
            m_view.setNumber(3,6,2);
            m_view.setNumber(4,6,3);
            m_view.setNumber(5,6,4);
            m_view.setNumber(6,6,5);
            m_view.setNumber(7,6,6);

            //数据区域随机赋值
            for(int col = 2; col <= 5; col++)
                for(int row = 2; row <= 7; row++)
                	if(row == 3 && col==3){
                		 m_view.setFormula(row, col, "-1*rand()");
                	}else{
                		
                		m_view.setFormula(row, col, "rand()");//rand()为excle随机函数
                	}
            //设置公式
            m_view.setFormula(7, 2, "SUM(C3:C7)");
            //选中单元格区域
            m_view.setSelection("C8:F8");
            //编辑复制 向右复制
            m_view.editCopyRight();

            //绘图区坐标addChart（左上列x，左上行y，右下列x，右下行y）
            ChartShape chart = m_view.addChart(8, 0, 15, 20.4);
            //图标形式
            chart.setChartType(ChartShape.TypeColumn);
            /*
            TypeCombination:方形
            TypeBar:横向柱状图
            TypePie:饼状图
            TypeLine:线状图
            TypeArea:面积图
            TypeDoughnut:圈图
            TypeScatter:线点图
            TypeBubble:没怎么看懂，就是一个灰图，不过查阅资料，貌似是泡状图
            */

            //设置连接区域
            chart.setLinkRange("Sheet1!$C$2", false);

            //添加一个系列
            chart.addSeries();
            //系列名字
            chart.setSeriesName(0, "Sheet1!$C$2");
            //系列值
            chart.setSeriesYValueFormula(0, "Sheet1!$C$3:$C$7");
            //系列分类
            chart.setCategoryFormula("Sheet1!$B$3:$B$7");

            chart.addSeries();
            chart.setSeriesName(1, "Sheet1!$D$2");
            chart.setSeriesYValueFormula(1, "Sheet1!$D$3:$D$7");

            chart.addSeries();
            chart.setSeriesName(2, "Sheet1!$E$2");
            chart.setSeriesYValueFormula(2, "Sheet1!$E$3:$E$7");

            chart.addSeries();
            chart.setSeriesName(3, "Sheet1!$F$2");
            chart.setSeriesYValueFormula(3, "Sheet1!$F$3:$F$7");

//            chart.getChart().validateData();

            //设置横坐标标题
            chart.setAxisTitle(ChartShape.XAxis, 0, "横坐标标题");
            //设置纵坐标标题
            chart.setAxisTitle(ChartShape.YAxis, 0, "纵坐标标题");

            //设置图表样式
            ChartFormat cf = chart.getChartFormat();
            //设置背景色
            cf.setPattern((short)1);
            cf.setPatternFG(Color.LIGHT_GRAY.getRGB());
            chart.setChartFormat(cf);
            //设置绘图区颜色
            cf = chart.getPlotFormat();
            cf.setPattern((short)1);
            cf.setPatternFG(new Color(204, 255, 255).getRGB());
            chart.setPlotFormat(cf);

            //设置横坐标文字大小
            cf = chart.getAxisFormat(ChartShape.XAxis, 0);
            cf.setFontSizeInPoints(8.5);
            chart.setAxisFormat(ChartShape.XAxis, 0, cf);

            //设置纵坐标文字大小
            cf = chart.getAxisFormat(ChartShape.YAxis, 0);
            cf.setFontSizeInPoints(8.5);
            chart.setAxisFormat(ChartShape.YAxis, 0, cf);

            //设置图标内标线样式
            cf = chart.getSeriesFormat(0);//地0个
            cf.setLineStyle((short)1);
            cf.setLineWeight(3*20);
            cf.setLineColor((new Color(0, 0, 128)).getRGB());
            cf.setMarkerAuto(false);
            cf.setMarkerStyle((short)0);
            chart.setSeriesFormat(0, cf);

            cf = chart.getSeriesFormat(1);
            cf.setLineStyle((short)1);
            cf.setLineWeight(3*20);
            cf.setLineColor((new Color(255, 0, 255)).getRGB());
            cf.setMarkerAuto(false);
            cf.setMarkerStyle((short)0);
            chart.setSeriesFormat(1, cf);

            cf = chart.getSeriesFormat(2);
            cf.setLineStyle((short)1);
            cf.setLineWeight(3*20);
            cf.setLineColor((new Color(255, 255, 0)).getRGB());
            cf.setMarkerAuto(false);
            cf.setMarkerStyle((short)0);
            chart.setSeriesFormat(2, cf);

            cf = chart.getSeriesFormat(3);
            cf.setLineStyle((short)1);
            cf.setLineWeight(3*20);
            cf.setLineColor((new Color(0, 255, 255)).getRGB());
            cf.setMarkerAuto(false);
            cf.setMarkerStyle((short)0);
            chart.setSeriesFormat(3, cf);

            //主格网
            cf = chart.getMajorGridFormat(ChartShape.YAxis, 0);
            cf.setLineStyle((short)2);
            cf.setLineColor((new Color(255, 0, 0)).getRGB());
            cf.setLineAuto();
            chart.setMajorGridFormat(ChartShape.YAxis, 0, cf);

            /**
             * LegendPlacementRight
             */
            //图利位置
            chart.setLegendPosition(ChartFormat.LegendPlacementRight);

            //图利样式
            cf = chart.getLegendFormat();
            cf.setFontBold(true);
            cf.setFontSizeInPoints(8);
            chart.setLegendFormat(cf);


            //excel写出路径
            m_view.write(outPath);
            System.out.println("end");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally
        {
            m_view.releaseLock();
        }
    }
}
