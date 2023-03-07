package com.example.skincancer; // Place in the relevant package directory of your application.


/******************************
* Copyright (c) 2003--2022 Kevin Lano
* This program and the accompanying materials are made available under the
* terms of the Eclipse Public License 2.0 which is available at
* http://www.eclipse.org/legal/epl-2.0
*
* SPDX-License-Identifier: EPL-2.0
* *****************************/
/* OCL library for Java version 8+ */


import android.util.Log;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.function.Function;

 @FunctionalInterface
 interface Evaluation<S,T> {
      T evaluate(S s);
 }

class OclMaplet<K,T>
{ K key;
  T value;

  OclMaplet(K k, T v)
  { key = k;
    value = v;
  }
}

  public class Ocl
  { public static long getTime()
    { Date d = new Date();
      return d.getTime();
    }

    public static void displayString(String s)
    { Log.i("Ocl", s); }

    public static void displayint(int i)
    { Log.i("Ocl", "" + i); }

    public static void displaylong(long i)
    { Log.i("Ocl", "" + i); }

    public static void displayboolean(boolean b)
    { Log.i("Ocl", "" + b); }

    public static void displaydouble(double d)
    { Log.i("Ocl", "" + d); }

    public static void displaySequence(List s)
    { Log.i("Ocl", s.toString()); }

    public static void displaySet(Set s)
    { Log.i("Ocl", s.toString()); }

    public static void displayMap(Map s)
    {Log.i("Ocl", s.toString()); }

    public static void displayOclProcess(OclProcess s)
    { Log.i("Ocl", s.actualThread.toString()); }

    public static void displayOclType(OclType s)
    { Log.i("Ocl", s.actualMetatype.toString()); }


    public static <T> HashSet<T> selectSet(Set<T> s, Predicate<T> f)
    { HashSet<T> result = new HashSet<T>();
      for (T x : s)
      { if (f.test(x))
        { result.add(x); }
      }
      return result;
    }

    public static <T> HashSet<T> rejectSet(Set<T> s, Predicate<T> f)
    { HashSet<T> result = new HashSet<T>();
      for (T x : s)
      { if (f.test(x))     { 
    	//catch
    }
        else
		{ result.add(x); }
      }
      return result;
    }

    public static <S,T> HashSet<T> collectSet(Set<S> s, Function<S,T> f)
    { HashSet<T> result = new HashSet<T>();
      for (S x : s)
      { result.add(f.apply(x)); }
      return result;
    }

    public static <T> ArrayList<T> selectSequence(ArrayList<T> s, Predicate<T> f)
    { ArrayList<T> result = new ArrayList<T>();
      for (int i = 0; i < s.size(); i++)
      { T x = (T) s.get(i);
        if (f.test(x))
        { result.add(x); }
      }
      return result;
    }

    public static <T> ArrayList<T> rejectSequence(ArrayList<T> s, Predicate<T> f)
    { ArrayList<T> result = new ArrayList<T>();
      for (int i = 0; i < s.size(); i++)
      { T x = (T) s.get(i);
        if (f.test(x))     { 
    	//catch
    }
        else
        { result.add(x); }
      }
      return result;
    }

    public static <S,T> ArrayList<T> collectSequence(List<S> s, Function<S,T> f)
    { ArrayList<T> result = new ArrayList<T>();
      for (int i = 0; i < s.size(); i++)
      { S x = (S) s.get(i);
        result.add(f.apply(x));
      }
      return result;
    }

    public static <T> boolean forAll(Collection<T> s, Predicate<T> f)
    { boolean result = true;
      for (T x : s)
      { if (f.test(x))     { 
    	//catch
    }
        else
        { return false; }
      }
      return result;
    }

    public static <T> boolean exists(Collection<T> s, Predicate<T> f)
    { boolean result = false;
      for (T x : s)
      { if (f.test(x))
        { return true; }
      }
      return result;
    }

    public static <T> boolean exists1(Collection<T> s, Predicate<T> f)
    { boolean found = false;
      for (T x : s)
      { if (f.test(x))
        { if (found)
          { return false; }
          found = true;
        }
      }
      return found;
    }

    public static <T> T any(Collection<T> s, Predicate<T> f)
    { for (T x : s)
      { if (f.test(x))
        { return x; }
      }
      return null;
    }

    public static <S,T> HashSet<T> unionAll(Collection<S> s, Function<S,Collection<T>> f)
    { HashSet<T> result = new HashSet<T>();
      for (S x : s)
      { result.addAll(f.apply(x)); }
      return result;
    }

    public static <S,T> ArrayList<T> concatenateAll(List<S> s, Function<S,List<T>> f)
    { ArrayList<T> result = new ArrayList<T>();
      for (int i = 0; i < s.size(); i++)
      { S x = (S) s.get(i);
        result.addAll(f.apply(x));
      }
      return result;
    }

    public static <S,T> HashSet<T> intersectAllSet(Collection<S> s, Function<S,Set<T>> f)
    { HashSet<T> result = new HashSet<T>();
      if (s.size() == 0) { return result; }
      S s1 = Ocl.any(s);
      result.addAll(f.apply(s1));
      for (S x : s)
      { result.retainAll(f.apply(x)); }
      return result;
    }

    public static <S,T> ArrayList<T> intersectAllSequence(Collection<S> s, Function<S,List<T>> f)
    { ArrayList<T> result = new ArrayList<T>();
      if (s.size() == 0) { return result; }
      S s1 = Ocl.any(s);
      result.addAll(f.apply(s1));
      for (S x : s)
      { result.retainAll(f.apply(x)); }
      return result;
    }

    public static <T> HashSet<T> initialiseSet(T ... args)
    { HashSet<T> result = new HashSet<T>();
      for (int i = 0; i < args.length; i++)
      { result.add(args[i]); }
      return result;
    }

    public static <T> ArrayList<T> initialiseSequence(T ... args)
    { ArrayList<T> result = new ArrayList<T>();
      for (int i = 0; i < args.length; i++)
      { result.add(args[i]); }
      return result;
    }

    public static <K,T> HashMap<K,T> initialiseMap(OclMaplet ... args)
    { HashMap<K,T> result = new HashMap<K,T>();
      for (int i = 0; i < args.length; i++)
      { OclMaplet<K,T> x = (OclMaplet<K,T>) args[i];
        result.put(x.key, x.value);
      }
      return result;
    }

	public static <T> HashSet<T> copySet(Collection<T> s)
     { HashSet<T> result = new HashSet<T>();
       result.addAll(s);
       return result;
     }

    public static <T> TreeSet<T> copySortedSet(Collection<T> s)
    { TreeSet<T> result = new TreeSet<T>();
      result.addAll(s);
      return result;
    }

	public static <T> ArrayList<T> copySequence(Collection<T> s)
	{ ArrayList<T> result = new ArrayList<T>();
      result.addAll(s);
	  return result;
	}

	public static <K,T> HashMap<K,T> copyMap(Map<K,T> s)
	{ HashMap<K,T> result = new HashMap<K,T>();
      result.putAll(s);
	  return result;
	}

    public static <T extends Comparable> T min(Collection<T> s)
    { ArrayList<T> slist = new ArrayList<T>();
      slist.addAll(s);
      T result = slist.get(0);
      for (int i = 1; i < slist.size(); i++)
      { T val = slist.get(i);
        if (val.compareTo(result) < 0)
        { result = val; }
      }
      return result;
    }

    public static <T extends Comparable> T max(Collection<T> s)
    { ArrayList<T> slist = new ArrayList<T>();
      slist.addAll(s);
      T result = slist.get(0);
      for (int i = 1; i < slist.size(); i++)
      { T val = slist.get(i);
        if (0 < val.compareTo(result))
        { result = val; }
      }
      return result;
    }

    public static long gcd(long xx, long yy)
    { long x = Math.abs(xx);
      long y = Math.abs(yy);
      while (x != 0 && y != 0)
      { long z = y;
        y = x % y;
        x = z;
      }

      if (y == 0)
      { return x; }

      if (x == 0)
      { return y; }

      return 0;
    }



    public static <T> HashSet<T> addSet(HashSet<T> s, T x)
    { if (x != null) { s.add(x); }
      return s;
    }

    public static HashSet<Integer> addSet(HashSet<Integer> s, int x)
    { s.add(new Integer(x));
      return s;
    }

    public static HashSet<Double> addSet(HashSet<Double> s, double x)
    { s.add(new Double(x));
      return s;
    }

    public static HashSet<Long> addSet(HashSet<Long> s, long x)
    { s.add(new Long(x));
      return s;
    }

    public static HashSet<Boolean> addSet(HashSet<Boolean> s, boolean x)
    { s.add(new Boolean(x));
      return s;
    }

    public static <T> ArrayList<T> addSequence(ArrayList<T> s, T x)
    { if (x != null) { s.add(x); }
      return s;
    }

    public static ArrayList<Integer> addSequence(ArrayList<Integer> s, int x)
    { s.add(new Integer(x));
      return s;
    }

    public static ArrayList<Double> addSequence(ArrayList<Double> s, double x)
    { s.add(new Double(x));
      return s;
    }

    public static ArrayList<Long> addSequence(ArrayList<Long> s, long x)
    { s.add(new Long(x));
      return s;
    }

    public static ArrayList<Boolean> addSequence(ArrayList<Boolean> s, boolean x)
    { s.add(new Boolean(x));
      return s;
    }

    public static <T> HashSet<T> excludingSet(Set<T> s, T x)
    { HashSet<T> result = new HashSet<T>();
      result.addAll(s);
      HashSet<T> rem = new HashSet<T>();
      rem.add(x);
      result.removeAll(rem);
      return result;
    }

    public static <T> ArrayList<T> excludingSequence(List<T> s, T x)
    { ArrayList<T> result = new ArrayList<T>();
      result.addAll(s);
      HashSet<T> rem = new HashSet<T>();
      rem.add(x);
      result.removeAll(rem);
      return result;
    }

    public static <T> ArrayList<T> asSequence(Collection<T> c)
    { ArrayList<T> res = new ArrayList<T>();
      res.addAll(c);
      return res;
    }

    public static <T> ArrayList<T> asOrderedSet(Collection<T> c)
    { ArrayList<T> res = new ArrayList<T>();
      for (T x : c)
      { if (res.contains(x))     { 
    	//catch
    }
        else
        { res.add(x); }
      }
      return res;
    }

    public static <T> HashSet<T> asSet(Collection<T> c)
    { HashSet<T> res = new HashSet<T>();
      res.addAll(c);
      return res;
    }

    public static <T> ArrayList<T> asBag(Collection<T> c)
    { ArrayList<T> res = new ArrayList<T>();
      res.addAll(c);
      Collections.shuffle(res);
      return res;
    }


  public static <T> HashSet<T> union(HashSet<T> a, Collection<T> b)
  { HashSet<T> res = new HashSet<T>();
    res.addAll(a); res.addAll(b);
    return res;
  }

  public static <T> TreeSet<T> union(TreeSet<T> a, Collection<T> b)
  { TreeSet<T> res = new TreeSet<T>();
    res.addAll(a); res.addAll(b);
    return res;
  }

  public static <T> ArrayList<T> union(ArrayList<T> a, Collection<T> b)
  { ArrayList<T> res = new ArrayList<T>();
    res.addAll(a); res.addAll(b);
    return res;
  }


  public static <T> HashSet<T> subtract(HashSet<T> a, Collection<T> b)
  { HashSet<T> res = new HashSet<T>();
    res.addAll(a);
    res.removeAll(b);
    return res;
  }

  public static <T> TreeSet<T> subtract(TreeSet<T> a, Collection<T> b)
  { TreeSet<T> res = new TreeSet<T>();
    res.addAll(a);
    res.removeAll(b);
    return res;
  }

  public static <T> ArrayList<T> subtract(ArrayList<T> a, Collection<T> b)
  { ArrayList<T> res = new ArrayList<T>();
    res.addAll(a);
    res.removeAll(b);
    return res;
  }

  public static String subtract(String a, String b)
  { String res = "";
    for (int i = 0; i < a.length(); i++)
    { if (b.indexOf(a.charAt(i)) < 0)
      { res = res + a.charAt(i); }
    }
    return res;
  }



  public static <T> HashSet<T> intersection(HashSet<T> a, Collection<T> b)
  { HashSet<T> res = new HashSet<T>();
    res.addAll(a);
    res.retainAll(b);
    return res;
  }

  public static <T> TreeSet<T> intersection(TreeSet<T> a, Collection<T> b)
  { TreeSet<T> res = new TreeSet<T>();
    res.addAll(a);
    res.retainAll(b);
    return res;
  }

  public static <T> ArrayList<T> intersection(ArrayList<T> a, Collection<T> b)
  { ArrayList<T> res = new ArrayList<T>();
    res.addAll(a);
    res.retainAll(b);
    return res;
  }



  public static <T> Set<T> symmetricDifference(Collection<T> a, Collection<T> b)
  { Set<T> res = new HashSet<T>();
    for (T x : a)
    { if (b.contains(x))     { 
    	//catch
    }
      else { res.add(x); }
    }
    for (T y : b)
    { if (a.contains(y))     { 
    	//catch
    }
      else { res.add(y); }
    }
    return res;
  }



  public static <T> boolean isUnique(Collection<T> evals)
  { HashSet<T> vals = new HashSet<T>();
    for (T ob : evals)
    { if (vals.contains(ob)) { return false; }
      vals.add(ob);
    }
    return true;
  }


  public static int sumint(Collection<Integer> a)
  { int sum = 0;
    for (Integer x : a)
    { if (x != null)
      { sum += x.intValue(); }
    }
    return sum;
  }

  public static double sumdouble(Collection<Double> a)
  { double sum = 0.0;
    for (Double x : a)
    { if (x != null)
      { sum += x.doubleValue(); }
    }
    return sum;
  }

  public static long sumlong(Collection<Long> a)
  { long sum = 0;
    for (Long x : a)
    { if (x != null)
      { sum += x.longValue(); }
    }
    return sum;
  }

  public static String sumString(Collection<String> a)
  { String sum = "";
    for (String x : a)
    { sum = sum + x; }
    return sum;
  }



  public static int prdint(Collection<Integer> a)
  { int prd = 1;
    for (Integer x : a)
    { if (x != null) { prd *= x.intValue(); }
    }
    return prd;
  }

  public static double prddouble(Collection<Double> a)
  { double prd = 1;
    for (Double x : a)
    { if (x != null) { prd *= x.doubleValue(); }
    }
    return prd;
  }

  public static long prdlong(Collection<Long> a)
  { long prd = 1;
    for (Long x : a)
    { if (x != null) { prd *= x.longValue(); }
    }
    return prd;
  }



  public static <T> ArrayList<T> concatenate(Collection<T> a, Collection<T> b)
  { ArrayList<T> res = new ArrayList<T>();
    res.addAll(a);
    res.addAll(b);
    return res;
  }

  public static <T> ArrayList<T> reverse(Collection<T> a)
  { ArrayList<T> res = new ArrayList<T>();
    res.addAll(a);
    Collections.reverse(res);
    return res;
  }

  public static String reverse(String a)
  { String res = "";
    for (int i = a.length() - 1; i >= 0; i--)
    { res = res + a.charAt(i); }
    return res;
  }

  public static <T> ArrayList<T> front(List<T> a)
  { ArrayList<T> res = new ArrayList<T>();
    for (int i = 0; i < a.size() - 1; i++)
    { res.add(a.get(i)); }
    return res;
  }


  public static <T> ArrayList<T> tail(List<T> a)
  { ArrayList<T> res = new ArrayList<T>();
    for (int i = 1; i < a.size(); i++)
    { res.add(a.get(i)); }
    return res;
  }


  public static <T extends Comparable> ArrayList<T> sort(Collection<T> a)
  { ArrayList<T> res = new ArrayList<T>();
    res.addAll(a);
    Collections.sort(res);
    return res;
  }

  public static <T> ArrayList<T> sortedBy(final ArrayList<T> a, ArrayList<? extends Comparable> f)
  { int i = a.size()-1;
    Map<T,Comparable> fMap = new HashMap<T,Comparable>();
    for (int j = 0; j < a.size(); j++)
    { fMap.put(a.get(j), (Comparable) f.get(j)); }
    return mergeSort(a,fMap,0,i);
  }

  static <T> ArrayList<T> mergeSort(final ArrayList<T> a, Map<T,Comparable> f, int ind1, int ind2)
  { ArrayList<T> res = new ArrayList<T>();
    if (ind1 > ind2)
    { return res; }
    if (ind1 == ind2)
    { res.add(a.get(ind1));
      return res;
    }
    if (ind2 == ind1 + 1)
    { Comparable e1 = (Comparable) f.get(a.get(ind1));
      Comparable e2 = (Comparable) f.get(a.get(ind2));
      if (e1.compareTo(e2) < 0) // e1 < e2
      { res.add(a.get(ind1)); res.add(a.get(ind2)); return res; }
      else
      { res.add(a.get(ind2)); res.add(a.get(ind1)); return res; }
    }
    int mid = (ind1 + ind2)/2;
    ArrayList<T> a1;
    ArrayList<T> a2;
    if (mid == ind1)
    { a1 = new ArrayList<T>();
      a1.add(a.get(ind1));
      a2 = mergeSort(a,f,mid+1,ind2);
    }
    else
    { a1 = mergeSort(a,f,ind1,mid-1);
      a2 = mergeSort(a,f,mid,ind2);
    }
    int i = 0;
    int j = 0;
    while (i < a1.size() && j < a2.size())
    { Comparable e1 = (Comparable) f.get(a1.get(i));
      Comparable e2 = (Comparable) f.get(a2.get(j));
      if (e1.compareTo(e2) < 0) // e1 < e2
      { res.add(a1.get(i));
        i++; // get next e1
      }
      else
      { res.add(a2.get(j));
        j++;
      }
    }
    if (i == a1.size())
    { for (int k = j; k < a2.size(); k++)
      { res.add(a2.get(k)); }
    }
    else
    { for (int k = i; k < a1.size(); k++)
      { res.add(a1.get(k)); }
    }
    return res;
  }


  public static ArrayList<Integer> integerSubrange(int i, double j)
  { ArrayList<Integer> tmp = new ArrayList<Integer>();
    for (int k = i; k <= j; k++)
    { tmp.add(new Integer(k)); }
    return tmp;
  }

  public static String subrange(String s, int i, int j)
  { if (i > j)
    { return ""; }
    int lind = i;
    if (i < 1)
    { lind = 1; }
    int uind = j;
    if (j > s.length())
    { uind = s.length(); }
    return s.substring(lind-1,uind);
  }

  public static <T> ArrayList<T> subrange(List<T> l, int i, int j)
  { ArrayList<T> tmp = new ArrayList<T>();
    for (int k = i-1; k < j; k++)
    { tmp.add(l.get(k)); }
    return tmp;
  }

  public static String subrange(String s, int i)
  { if (i > s.length())
    { return ""; }
    int lind = i;
    if (i < 1)
    { lind = 1; }
    return s.substring(lind-1);
  }

  public static <T> ArrayList<T> subrange(List<T> l, int i)
  { ArrayList<T> tmp = new ArrayList<T>();
    for (int k = i-1; k < l.size(); k++)
    { tmp.add(l.get(k)); }
    return tmp;
  }



  public static <T> ArrayList<T> prepend(List<T> l, T ob)
  { ArrayList<T> res = new ArrayList<T>();
    res.add(ob);
    res.addAll(l);
    return res;
  }


  public static <T> ArrayList<T> append(List<T> l, T ob)
  { ArrayList<T> res = new ArrayList<T>();
    res.addAll(l);
    res.add(ob);
    return res;
  }

  public static <T> ArrayList<T> includingSequence(List<T> l, T ob)
  { ArrayList<T> res = new ArrayList<T>();
    res.addAll(l);
    res.add(ob);
    return res;
  }

  public static <T> HashSet<T> includingSet(Set<T> l, T ob)
  { HashSet<T> res = new HashSet<T>();
    res.addAll(l);
    res.add(ob);
    return res;
  }

  public static <T> int count(Collection<T> l, T obj)
  { return Collections.frequency(l,obj); }

  public static int count(String s, String x)
  { int res = 0;
    if ("".equals(s)) { return res; }
    int ind = s.indexOf(x);
    if (ind == -1) { return res; }
    String ss = s.substring(ind+1,s.length());
    res++;
    while (ind >= 0)
    { ind = ss.indexOf(x);
      if (ind == -1 || ss.equals(""))
	  { return res; }
      res++;
      ss = ss.substring(ind+1,ss.length());
    }
    return res;
  }



  public static ArrayList<String> characters(String str)
  { char[] chars = str.toCharArray();
    ArrayList<String> res = new ArrayList<String>();
    for (int i = 0; i < chars.length; i++)
    { res.add("" + chars[i]); }
    return res;
  }



    public static <T> T any(Collection<T> v)
    { for (T o : v) { return o; }
      return null;
    }


    public static <T> T first(Collection<T> v)
    { for (T o : v) { return o; }
      return null;
    }


    public static <T> T last(List<T> v)
    { if (v.isEmpty()) { return null; }
      return v.get(v.size() - 1);
    }



    public static <T> ArrayList<List<T>> subcollections(ArrayList<T> v)
    { ArrayList<List<T>> res = new ArrayList<List<T>>();
      if (v.isEmpty())
      { res.add(new ArrayList<T>()); return res; }
      if (v.size() == 1)
      { res.add(new ArrayList<T>()); res.add(v); return res;
       }
      ArrayList<T> s = new ArrayList<T>();
      T x = v.get(0);
      s.addAll(v);
      s.remove(0);
      ArrayList<List<T>> scs = subcollections(s);
      res.addAll(scs);
      for (int i = 0; i < scs.size(); i++)
      { ArrayList<T> sc = (ArrayList<T>) scs.get(i);
        ArrayList<T> scc = new ArrayList<T>();
        scc.add(x); scc.addAll(sc); res.add(scc);
      }
      return res;
    }

    public static <T> HashSet<Set<T>> subcollections(HashSet<T> v)
    { HashSet<Set<T>> res = new HashSet<Set<T>>();
      if (v.isEmpty()) { res.add(new HashSet<T>()); return res; }
      if (v.size() == 1) { res.add(new HashSet<T>()); res.add(v); return res;
       }
      HashSet<T> s = new HashSet<T>();
      T x = null; 
      int i = 0;
      for (T o : v)
      { if (i == 0) { x = o; i++; }
         else { s.add(o); }
      }
      HashSet<Set<T>> scs = subcollections(s);
      res.addAll(scs);
      for (Set<T> obj : scs)
      { HashSet<T> sc = (HashSet<T>) obj;
        HashSet<T> scc = new HashSet<T>();
        scc.add(x); scc.addAll(sc); res.add(scc);
      }
      return res;
    }


  public static <T extends Comparable<T>> ArrayList<T> maximalElements(List<T> s, List<Comparable> v)
  { ArrayList<T> res = new ArrayList<T>();
    if (s.size() == 0) { return res; }
    Comparable largest = v.get(0);
    res.add(s.get(0));

    for (int i = 1; i < s.size(); i++)
    { Comparable next = v.get(i);
      if (largest.compareTo(next) < 0)
      { largest = next;
        res.clear();
        res.add(s.get(i));
      }
      else if (largest.compareTo(next) == 0)
      { res.add(s.get(i)); }
    }
    return res;
  }

  public static <T> ArrayList<T> minimalElements(ArrayList<T> s, ArrayList<Comparable> v)
  { ArrayList<T> res = new ArrayList<T>();
    if (s.size() == 0) { return res; }
    Comparable smallest = v.get(0);
    res.add(s.get(0));

    for (int i = 1; i < s.size(); i++)
    { Comparable next = v.get(i);
      if (next.compareTo(smallest) < 0)
      { smallest = next;
        res.clear();
        res.add(s.get(i));
      }
      else if (smallest.compareTo(next) == 0)
      { res.add(s.get(i)); }
    }
    return res;
  }


  public static <T> HashSet<T> intersectAll(Collection<Collection<T>> se)
  { HashSet<T> res = new HashSet<T>();
    if (se.size() == 0) { return res; }
    res.addAll((Collection<T>) Ocl.any(se));
    for (Collection<T> o : se)
    { res.retainAll(o); }
    return res;
  }



  public static <T> HashSet<T> unionAll(Collection<Collection<T>> se)
  { HashSet<T> res = new HashSet<T>();
    for (Collection<T> o : se)
    { res.addAll(o); }
    return res;
  }

    public static <T> ArrayList<T> concatenateAll(ArrayList<Collection<T>> a)
    { ArrayList<T> res = new ArrayList<T>();
      for (int i = 0; i < a.size(); i++)
      { Collection<T> r = (Collection<T>) a.get(i);
        res.addAll(r);
      }
      return res;
    }



  public static <T> ArrayList<T> insertAt(List<T> l, int ind, T ob)
  { ArrayList<T> res = new ArrayList<T>();
    for (int i = 0; i < ind-1 && i < l.size(); i++)
    { res.add(l.get(i)); }
    if (ind <= l.size() + 1) { res.add(ob); }
    for (int i = ind-1; i < l.size(); i++)
    { res.add(l.get(i)); }
    return res;
  }

  public static <T> ArrayList<T> insertInto(List<T> l, int ind, List<T> obs)
  { ArrayList<T> res = new ArrayList<T>();

    if (ind <= 0)
    { ind = 1; }
    if (ind > l.size())
    { ind = l.size()+1; }

    for (int i = 0; i < ind-1 && i < l.size(); i++)
    { res.add(l.get(i)); }

    if (ind <= l.size() + 1)
    { for (int j = 0; j < obs.size(); j++)
      { res.add(obs.get(j)); }
    }

    for (int i = ind-1; i < l.size(); i++)
    { res.add(l.get(i)); }

    return res;
  }

  public static <T> ArrayList<T> removeAt(List<T> l, int ind)
  { ArrayList<T> res = new ArrayList<T>();
    res.addAll(l);
    if (ind <= res.size() && ind >= 1)
    { res.remove(ind - 1); }
    return res;
  }

  public static String removeAt(String ss, int ind)
  { StringBuffer sb = new StringBuffer(ss);
    if (ind <= ss.length() && ind >= 1)
    { sb.deleteCharAt(ind - 1); }
    return sb.toString();
  }

  public static <T> ArrayList<T> setAt(List<T> l, int ind, T val)
  { ArrayList<T> res = new ArrayList<T>();
    res.addAll(l);
    if (ind <= res.size() && ind >= 1)
    { res.set(ind - 1,val); }
    return res;
  }

  public static String setAt(String ss, int ind, String val)
  { StringBuffer sb = new StringBuffer(ss);
    if (ind <= ss.length() && ind >= 1 && val.length() > 0)
    { sb.setCharAt(ind - 1, val.charAt(0)); }
    return sb.toString();
  }

  public static <T> ArrayList<T> removeFirst(List<T> l, T obj)
  { ArrayList<T> res = new ArrayList<T>();
    res.addAll(l);
    res.remove(obj);
    return res;
  }

  public static String insertAt(String l, int ind, Object ob)
  { String res = "";
    for (int i = 0; i < ind-1 && i < l.length(); i++)
    { res = res + l.charAt(i); }
    if (ind <= l.length() + 1) { res = res + ob; }
    for (int i = ind-1; i < l.length(); i++)
    { res = res + l.charAt(i); }
    return res;
  }

  public static String byte2char(int b)
  { try { byte[] bb = {(byte) b};
      return new String(bb); }
    catch (Exception e)
    { return ""; }
  }

  public static int char2byte(String s)
  { if (s == null || s.length() == 0)
    { return -1; }
    return (int) s.charAt(0);
  }


 public static boolean isInteger(String str)
 { try { Integer.parseInt(str); return true; }
   catch (Exception e) { return false; }
 }


 public static boolean isReal(String str)
  { try { double d = Double.parseDouble(str);
          if (Double.isNaN(d)) { return false; }
          return true; }
    catch (Exception e) { return false; }
  }


 public static boolean isLong(String str)
  { try
    { Long.parseLong(str);
      return true;
    }
    catch (Exception e)
    { return false; }
  }


  public static boolean oclIsTypeOf(Object x, String e)
  { try {
      if (x.getClass() == Class.forName(e))
      { return true; }
      else
      { return false; }
    }
    catch (Exception ex) { return false; }
  }


  public static String before(String s, String sep)
  { if (sep.length() == 0)
    { return s; }
    int ind = s.indexOf(sep);
    if (ind < 0)
    { return s; }
    return s.substring(0,ind);
  }


  public static String after(String s, String sep)
  { int ind = s.indexOf(sep);
    int seplength = sep.length();
    if (ind < 0)
	{ return ""; }
    if (seplength == 0)
	{ return ""; }
    return s.substring(ind + seplength, s.length());
  }

  public static String replace(String str, String delim, String rep)
  { String result = "";

    String s = str + "";
    int i = (s.indexOf(delim) + 1);
    if (i == 0)
    { return s; }

    int sublength = delim.length();
    if (sublength == 0)
    { return s; }

    while (i > 0)
    {
      result = result + Ocl.subrange(s,1,i - 1) + rep;
      s = Ocl.subrange(s,i + delim.length(),s.length());
      i = (s.indexOf(delim) + 1);
    }
    result = result + s;
    return result;
  }

  public static ArrayList<String> split(String str, String delim)
  { String[] splits = str.split(delim);

    ArrayList<String> res = new ArrayList<String>();

    for (int j = 0; j < splits.length; j++)
    { if (splits[j].length() > 0)
      { res.add(splits[j]); }
    }
    return res;
  }

  public static ArrayList<String> allMatches(String str, String regex)
  { java.util.regex.Pattern patt = java.util.regex.Pattern.compile(regex);
    java.util.regex.Matcher matcher = patt.matcher(str);
    ArrayList<String> res = new ArrayList<String>();
    while (matcher.find())
    { res.add(matcher.group() + ""); }
    return res;
  }

  public static String firstMatch(String str, String regex)
  { java.util.regex.Pattern patt = java.util.regex.Pattern.compile(regex);
    java.util.regex.Matcher matcher = patt.matcher(str);
    String res = null;
    if (matcher.find())
    { res = matcher.group() + ""; }
    return res;
  }


  public static String replaceAll(String str, String regex, String rep)
  { java.util.regex.Pattern patt = java.util.regex.Pattern.compile(regex);
    java.util.regex.Matcher matcher = patt.matcher(str);
    return matcher.replaceAll(rep);
  }

  public static String replaceFirstMatch(String str, String regex, String rep)
  { java.util.regex.Pattern patt = java.util.regex.Pattern.compile(regex);
    java.util.regex.Matcher matcher = patt.matcher(str);
    return matcher.replaceFirst(rep);
  }


  public static boolean hasMatch(String str, String regex)
  { java.util.regex.Pattern patt = java.util.regex.Pattern.compile(regex);
    java.util.regex.Matcher matcher = patt.matcher(str);
    if (matcher.find())
    { return true; }
    return false;
  }

  public static boolean isMatch(String str, String regex)
  { return str.matches(regex); }


  public static <D,R> HashSet<D> mapKeys(Map<D,R> m)
  { HashSet<D> res = new HashSet<D>();
    Set<D> keys = m.keySet();

    for (D key : keys)
    { res.add(key); }

    return res;
  }

  public static <D,R> HashMap<D,R> includingMap(Map<D,R> m, D src, R trg)
  { HashMap<D,R> copy = new HashMap<D,R>();
    copy.putAll(m);
    copy.put(src,trg);
    return copy;
  }


  public static <D,R> HashMap<D,R> excludeAllMap(Map<D,R> m1, Map m2)
  { // m1 - m2
    HashMap<D,R> res = new HashMap<D,R>();
    Set<D> keys = m1.keySet();

    for (D key : keys)
    { if (m2.containsKey(key))
          { 
    	//catch
    }
      else
      { res.put(key,m1.get(key));  }
    }
    return res;
  }


  public static <D,R> HashMap<D,R> excludingMapKey(Map<D,R> m, D k)
  { // m - { k |-> m(k) }
    HashMap<D,R> res = new HashMap<D,R>();
    res.putAll(m);
    res.remove(k);
    return res;
  }


  public static <D,R> HashMap<D,R> excludingMapValue(Map<D,R> m, R v)
  { // m - { k |-> v }
    HashMap<D,R> res = new HashMap<D,R>();
    Set<D> keys = m.keySet();

    for (D key : keys)
    { if (v.equals(m.get(key)))
          { 
    	//catch
    }
      else
      { res.put(key,m.get(key));  }
    }
    return res;
  }


  public static <D,R> HashMap<D,R> unionMap(Map<D,R> m1, Map<D,R> m2)
  { HashMap<D,R> res = new HashMap<D,R>();
    res.putAll(m1);
    res.putAll(m2);
    return res;
  }


  public static <D,R> HashMap<D,R> intersectionMap(Map<D,R> m1, Map m2)
  { HashMap<D,R> res = new HashMap<D,R>();
    Set<D> keys = m1.keySet();

    for (D key : keys)
    { if (m2.containsKey(key) && m1.get(key) != null &&
          m1.get(key).equals(m2.get(key)))
      { res.put(key,m1.get(key));  }
    }
    return res;
  }

  public static <D,R> HashMap<D,R> restrictMap(Map<D,R> m1, Set<D> ks)
  { HashMap<D,R> res = new HashMap<D,R>();
    Set<D> keys = m1.keySet();

    for (D key : keys)
    { if (ks.contains(key))
      { res.put(key,m1.get(key));  }
    }
    return res;
  }

  public static <D,R> HashMap<D,R> antirestrictMap(Map<D,R> m1, Set<D> ks)
  { HashMap<D,R> res = new HashMap<D,R>();
    Set<D> keys = m1.keySet();

    for (D key : keys)
    { if (ks.contains(key))     { 
    	//catch
    }
      else
      { res.put(key,m1.get(key));  }
    }
    return res;
  }

  public static <D,R> HashMap<D,R> selectMap(Map<D,R> m, Predicate<R> f)
  { HashMap<D,R> result = new HashMap<D,R>();
    Set<D> keys = m.keySet();
    for (D k : keys)
    { R value = m.get(k);
      if (f.test(value))
      { result.put(k,value); }
    }
    return result;
  }

  public static <D,R> HashMap<D,R> rejectMap(Map<D,R> m, Predicate<R> f)
  { HashMap<D,R> result = new HashMap<D,R>();
    Set<D> keys = m.keySet();
    for (D k : keys)
    { R value = m.get(k);
      if (f.test(value)) {
      	//value
      }
      else
      { result.put(k,value); }
    }
    return result;
  }

   public static <R,T> HashMap<String,T> collectMap(HashMap<String,R> m, Function<R,T> f)
    { HashMap<String,T> result = new HashMap<String,T>();
      Set<String> keys = m.keySet();
      for (String k : keys)
      { R value = m.get(k);
        result.put(k, f.apply(value));
      }
      return result;
    }

  public static <D,R> boolean existsMap(Map<D,R> m, Predicate<R> f)
  { Set<D> keys = m.keySet();
    for (D k : keys)
    { R value = m.get(k);
      if (f.test(value))
      { return true; }
    }
    return false;
  }

  public static <D,R> boolean exists1Map(Map<D,R> m, Predicate<R> f)
  { boolean found = false;
    Set<D> keys = m.keySet();
    for (D k : keys)
    { R value = m.get(k);
      if (f.test(value))
      { if (found)
        { return false; }
        found = true;
      }
    }
    return found;
  }

  public static <D,R> boolean forAllMap(Map<D,R> m, Predicate<R> f)
  { Set<D> keys = m.keySet();
    for (D k : keys)
    { R value = m.get(k);
      if (f.test(value))     { 
    	//catch
    }
      else
      { return false; }
    }
    return true;
  }

  public static <D,R> HashMap<D,R> addMap(HashMap<D,R> m, D key, R value)
  { m.put(key,value);
    return m;
  }

/* Operations for manipulating Ref(T) values, ie., pointers */

  public static int[] newRefint(int x)
  { int[] res = new int[1];
    res[0] = x;
    return res;
  }

  public static long[] newReflong(long x)
  { long[] res = new long[1];
    res[0] = x;
    return res;
  }

  public static double[] newRefdouble(double x)
  { double[] res = new double[1];
    res[0] = x;
    return res;
  }

  public static boolean[] newRefboolean(boolean x)
  { boolean[] res = new boolean[1];
    res[0] = x;
    return res;
  }

  public static String[] newRefString(String x)
  { String[] res = new String[1];
    res[0] = x;
    return res;
  }

  public static Object[] newRefObject(Object x)
  { Object[] res = new Object[1];
    res[0] = x;
    return res;
  }


  public static <T> ArrayList<T> sequenceRange(T[] arr, int n)
  { ArrayList<T> res = new ArrayList<T>();
    for (int i = 0; i < n; i++)
    { res.add(arr[i]); }
    return res;
  }

  public static int sequenceCompare(List sq1, List sq2)
  { int res = 0;
    for (int i = 0; i < sq1.size() && i < sq2.size(); i++)
    { Object elem1 = sq1.get(i);
      if (((Comparable) elem1).compareTo(sq2.get(i)) < 0)
      { return -1; }
      else if (((Comparable) elem1).compareTo(sq2.get(i)) > 0)
      { return 1; }
    }

    if (sq1.size() > sq2.size())
    { return 1; }
    if (sq2.size() > sq1.size())
    { return -1; }
    return res;
  }


 public static ArrayList<String> tokeniseCSV(String line)
 { // Assumes the separator is a comma
   StringBuffer buff = new StringBuffer();
   int x = 0;
   int len = line.length();
   boolean instring = false;
   ArrayList<String> res = new ArrayList<String>();
   while (x < len)
   { char chr = line.charAt(x);
     x++;
     if (chr == ',')
     { if (instring) { buff.append(chr); }
       else
       { res.add(buff.toString().trim());
         buff = new StringBuffer();
       }
     }
     else if ('"' == chr)
     { if (instring) { instring = false; }
       else { instring = true; }
     }
     else
     { buff.append(chr); }
   }
   res.add(buff.toString().trim());
   return res;
 }

 public static ArrayList<String> parseCSVtable(String rows)
 { StringBuffer buff = new StringBuffer();
   int x = 0;
   int len = rows.length();
   ArrayList<String> res = new ArrayList<String>();
   while (x < len)
   { char chr = rows.charAt(x);
     x++;
     if (chr == '\n')
     { res.add(buff.toString().trim());
       buff = new StringBuffer();
     }
     else
     { buff.append(chr); }
   }
   res.add(buff.toString().trim());
   return res;
 }
}
