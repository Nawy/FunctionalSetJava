import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Ivan Ermolaev
 * ermolaevym@gmail.com
 */
public class FuncSetTest {

    @Test
    public void test_singletonSet() {
        FuncSet.Set singletonSet = FuncSet.singletonSet(2);
        assertTrue(FuncSet.contains(singletonSet, 2));
    }

    @Test
    public void test_setFrom() {
        FuncSet.Set setObject = FuncSet.setFrom(1, 2, 3, 4);
        assertTrue(FuncSet.contains(setObject, 1));
        assertTrue(FuncSet.contains(setObject, 2));
        assertTrue(FuncSet.contains(setObject, 3));
        assertTrue(FuncSet.contains(setObject, 4));
    }

    @Test
    public void test_union() {
        FuncSet.Set set1 = FuncSet.setFrom(1, 2);
        FuncSet.Set set2 = FuncSet.setFrom(3, 4);
        FuncSet.Set unionSet = FuncSet.union(set1, set2);

        assertTrue(FuncSet.contains(unionSet, 1));
        assertTrue(FuncSet.contains(unionSet, 2));
        assertTrue(FuncSet.contains(unionSet, 3));
        assertTrue(FuncSet.contains(unionSet, 4));
    }

    @Test
    public void test_intersect() {
        FuncSet.Set set1 = FuncSet.setFrom(1, 2, 3, 4);
        FuncSet.Set set2 = FuncSet.setFrom(3, 4, 5, 6);
        FuncSet.Set intersectSet = FuncSet.intersect(set1, set2);

        assertTrue(FuncSet.contains(intersectSet, 3));
        assertTrue(FuncSet.contains(intersectSet, 4));
    }

    @Test
    public void test_diff() {
        FuncSet.Set set1 = FuncSet.setFrom(1, 2, 3, 4);
        FuncSet.Set set2 = FuncSet.setFrom(3, 4, 5, 6);
        FuncSet.Set diffSet = FuncSet.diff(set1, set2);

        assertTrue(FuncSet.contains(diffSet, 1));
        assertTrue(FuncSet.contains(diffSet, 2));
    }

    @Test
    public void test_filter() {
        FuncSet.Set numSet = FuncSet.setFrom(1, 2, 3, 4, 5);
        FuncSet.Set filteredSet = FuncSet.filter(numSet, x -> x % 2 != 0);

        assertTrue(FuncSet.contains(filteredSet, 1));
        assertTrue(FuncSet.contains(filteredSet, 3));
        assertTrue(FuncSet.contains(filteredSet, 5));
    }

    @Test
    public void test_checkForAll() {
        FuncSet.Set numSet = FuncSet.setFrom(1, 2, 3, 4, 5);

        assertTrue(FuncSet.checkForAll(numSet, x -> x > 0));
    }

    @Test
    public void test_checkForFirst() {
        FuncSet.Set numSet = FuncSet.setFrom(1, 2, 3, 4, 5);

        assertTrue(FuncSet.checkForFirst(numSet, x -> x % 2 == 0));
    }

}
