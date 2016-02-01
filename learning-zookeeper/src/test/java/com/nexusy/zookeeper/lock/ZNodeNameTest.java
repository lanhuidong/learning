package com.nexusy.zookeeper.lock;

import org.junit.Assert;
import org.junit.Test;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author lanhuidong
 * @since 2016-01-31
 */
public class ZNodeNameTest {

    @Test
    public void testOrderWithSamePrefix() {
        String[] names = {"x-5", "x-1", "x-3", "x-100", "x-7"};
        String[] expected = {"x-1", "x-3", "x-5", "x-7", "x-100"};
        assertOrderedNodeNames(names, expected);
    }

    @Test
    public void testOrderWithDifferentPrefixes() throws Exception {
        String[] names = {"r-3", "r-2", "r-1", "w-2", "w-1"};
        String[] expected = {"r-1", "r-2", "r-3", "w-1", "w-2"};
        assertOrderedNodeNames(names, expected);
    }

    @Test
    public void test(){
        SortedSet<ZNodeName> nodeNames = new TreeSet<>();
        String[] names = {"x-5", "x-1", "x-3", "x-100", "x-7"};
        for (String name : names) {
            nodeNames.add(new ZNodeName(name));
        }
        SortedSet<ZNodeName> lessThan = nodeNames.headSet(new ZNodeName("x-5"));
        System.out.println(lessThan.size());
    }

    protected void assertOrderedNodeNames(String[] names, String[] expected) {
        Assert.assertEquals("The two arrays should be the same size!", names.length, expected.length);
        SortedSet<ZNodeName> nodeNames = new TreeSet<>();
        for (String name : names) {
            nodeNames.add(new ZNodeName(name));
        }

        int index = 0;
        for (ZNodeName nodeName : nodeNames) {
            String name = nodeName.getName();
            Assert.assertEquals("Node " + index, expected[index], name);
            index++;
        }
    }
}
