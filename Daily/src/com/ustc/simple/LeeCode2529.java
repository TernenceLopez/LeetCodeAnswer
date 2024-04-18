package com.ustc.simple;

public class LeeCode2529 {
    public static void main(String[] args) {
        maximumCount(new int[]{-1997,-1994,-1984,-1983,-1973,-1953,-1912,-1908,-1896,-1890,-1880,-1879,-1875,-1870,-1852,-1833,-1827,-1822,-1795,-1790,-1789,-1748,-1743,-1738,-1734,-1731,-1730,-1730,-1727,-1720,-1719,-1718,-1713,-1711,-1707,-1698,-1694,-1690,-1680,-1677,-1656,-1655,-1641,-1626,-1623,-1597,-1582,-1563,-1542,-1541,-1523,-1508,-1507,-1497,-1495,-1492,-1487,-1477,-1472,-1466,-1456,-1451,-1445,-1445,-1437,-1411,-1402,-1396,-1392,-1382,-1378,-1363,-1353,-1348,-1340,-1330,-1312,-1310,-1306,-1306,-1293,-1281,-1273,-1262,-1262,-1261,-1239,-1232,-1195,-1194,-1190,-1190,-1180,-1177,-1173,-1159,-1147,-1132,-1126,-1107,-1069,-1068,-1055,-1048,-1044,-1037,-1033,-1033,-1025,-1018,-1013,-991,-990,-982,-970,-959,-958,-953,-934,-919,-912,-910,-910,-905,-897,-875,-858,-856,-850,-850,-828,-827,-812,-807,-790,-785,-781,-780,-766,-766,-764,-749,-745,-730,-697,-691,-671,-666,-649,-649,-630,-619,-616,-599,-591,-585,-571,-570,-535,-505,-496,-494,-487,-475,-472,-469,-450,-443,-438,-428,-421,-415,-414,-406,-404,-388,-382,-380,-378,-369,-362,-355,-350,-348,-346,-341,-330,-317,-297,-291,-279,-275,-270,-261,-257,-245,-241,-240,-238,-208,-197,-177,-174,-168,-123,-110,-93,-53,-53,-50,-46,-43,-40,-38,-34,-28,-19,-16,-15,-15,-12,-8,0,2,12,19,20,21,24,41,49,52,57,77,85,87,115,117,119,122,126,137,140,144,146,154,154,160,197,197,209,222,224,238,247,249,251,260,269,269,273,274,275,286,289,312,313,316,317,320,331,332,349,374,392,406,406,409,415,420,427,430,432,442,458,488,488,490,497,504,524,530,534,535,536,542,542,543,547,562,568,584,592,594,599,600,601,604,611,617,618,625,651,652,655,659,666,668,672,678,687,694,710,710,721,731,744,748,748,752,755,759,761,764,770,775,787,829,830,846,862,872,873,879,890,893,901,902,926,928,939,940,951,953,960,982,995,1021,1032,1032,1033,1042,1059,1071,1073,1076,1083,1086,1089,1093,1095,1099,1102,1111,1115,1127,1145,1146,1163,1164,1166,1167,1178,1187,1191,1195,1206,1207,1218,1219,1224,1227,1237,1244,1246,1246,1249,1251,1255,1257,1272,1275,1278,1281,1296,1306,1309,1310,1321,1328,1346,1352,1368,1369,1395,1398,1403,1427,1435,1441,1463,1471,1475,1478,1482,1494,1495,1501,1507,1508,1517,1535,1542,1548,1549,1560,1583,1584,1587,1589,1594,1607,1613,1618,1624,1631,1632,1637,1641,1648,1659,1676,1692,1723,1724,1726,1738,1744,1750,1751,1758,1761,1766,1770,1774,1776,1783,1788,1800,1801,1803,1809,1816,1822,1838,1838,1839,1839,1846,1854,1859,1862,1873,1889,1890,1917,1925,1926,1929,1929,1931,1932,1935,1938,1946,1947,1948,1956,1995,1995});
    }

    public static int maximumCount(int[] nums){
        int len = nums.length;
        int negCount = 0;
        int zeroCount = -1;
        for(int num: nums){
            if(num<0) negCount++;
            else if(num==0 && zeroCount==-1) zeroCount = negCount+1;
            else if(num==0 && zeroCount!=-1) zeroCount++;
            else break;
        }
        System.out.println(negCount);
        System.out.println(zeroCount);
        System.out.println(len-zeroCount+1);
        return zeroCount==-1?Math.max(negCount,len-negCount):Math.max(negCount, len-zeroCount);
    }
}