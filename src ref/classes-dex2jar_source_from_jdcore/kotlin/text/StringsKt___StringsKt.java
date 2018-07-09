package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt;
import kotlin.collections.Grouping;
import kotlin.collections.IndexedValue;
import kotlin.collections.IndexingIterable;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.collections.SlidingWindowKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000Þ\001\n\000\n\002\020\013\n\002\020\r\n\000\n\002\030\002\n\002\020\f\n\002\b\002\n\002\020\034\n\000\n\002\030\002\n\000\n\002\020$\n\002\b\003\n\002\030\002\n\002\b\005\n\002\020%\n\002\b\005\n\002\020 \n\002\020\016\n\000\n\002\020\b\n\002\b\020\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\030\002\n\002\b\r\n\002\020\037\n\002\b\007\n\002\030\002\n\002\b\004\n\002\020\002\n\002\b\006\n\002\020!\n\000\n\002\030\002\n\002\b\007\n\002\020\000\n\002\b\b\n\002\020\017\n\002\b\003\n\002\030\002\n\002\030\002\n\002\b\022\n\002\030\002\n\002\b\002\n\002\020\006\n\002\b\007\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\"\n\000\n\002\030\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\006\032!\020\000\032\0020\001*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032\n\020\006\032\0020\001*\0020\002\032!\020\006\032\0020\001*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032\020\020\007\032\b\022\004\022\0020\0050\b*\0020\002\032\020\020\t\032\b\022\004\022\0020\0050\n*\0020\002\032E\020\013\032\016\022\004\022\002H\r\022\004\022\002H\0160\f\"\004\b\000\020\r\"\004\b\001\020\016*\0020\0022\036\020\017\032\032\022\004\022\0020\005\022\020\022\016\022\004\022\002H\r\022\004\022\002H\0160\0200\004H\b\0323\020\021\032\016\022\004\022\002H\r\022\004\022\0020\0050\f\"\004\b\000\020\r*\0020\0022\022\020\022\032\016\022\004\022\0020\005\022\004\022\002H\r0\004H\b\032M\020\021\032\016\022\004\022\002H\r\022\004\022\002H\0160\f\"\004\b\000\020\r\"\004\b\001\020\016*\0020\0022\022\020\022\032\016\022\004\022\0020\005\022\004\022\002H\r0\0042\022\020\023\032\016\022\004\022\0020\005\022\004\022\002H\0160\004H\b\032N\020\024\032\002H\025\"\004\b\000\020\r\"\030\b\001\020\025*\022\022\006\b\000\022\002H\r\022\006\b\000\022\0020\0050\026*\0020\0022\006\020\027\032\002H\0252\022\020\022\032\016\022\004\022\0020\005\022\004\022\002H\r0\004H\b¢\006\002\020\030\032h\020\024\032\002H\025\"\004\b\000\020\r\"\004\b\001\020\016\"\030\b\002\020\025*\022\022\006\b\000\022\002H\r\022\006\b\000\022\002H\0160\026*\0020\0022\006\020\027\032\002H\0252\022\020\022\032\016\022\004\022\0020\005\022\004\022\002H\r0\0042\022\020\023\032\016\022\004\022\0020\005\022\004\022\002H\0160\004H\b¢\006\002\020\031\032`\020\032\032\002H\025\"\004\b\000\020\r\"\004\b\001\020\016\"\030\b\002\020\025*\022\022\006\b\000\022\002H\r\022\006\b\000\022\002H\0160\026*\0020\0022\006\020\027\032\002H\0252\036\020\017\032\032\022\004\022\0020\005\022\020\022\016\022\004\022\002H\r\022\004\022\002H\0160\0200\004H\b¢\006\002\020\030\032\032\020\033\032\b\022\004\022\0020\0350\034*\0020\0022\006\020\036\032\0020\037H\007\0324\020\033\032\b\022\004\022\002H 0\034\"\004\b\000\020 *\0020\0022\006\020\036\032\0020\0372\022\020\017\032\016\022\004\022\0020\002\022\004\022\002H 0\004H\007\032\032\020!\032\b\022\004\022\0020\0350\n*\0020\0022\006\020\036\032\0020\037H\007\0324\020!\032\b\022\004\022\002H 0\n\"\004\b\000\020 *\0020\0022\006\020\036\032\0020\0372\022\020\017\032\016\022\004\022\0020\002\022\004\022\002H 0\004H\007\032\r\020\"\032\0020\037*\0020\002H\b\032!\020\"\032\0020\037*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032\022\020#\032\0020\002*\0020\0022\006\020$\032\0020\037\032\022\020#\032\0020\035*\0020\0352\006\020$\032\0020\037\032\022\020%\032\0020\002*\0020\0022\006\020$\032\0020\037\032\022\020%\032\0020\035*\0020\0352\006\020$\032\0020\037\032!\020&\032\0020\002*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032!\020&\032\0020\035*\0020\0352\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032!\020'\032\0020\002*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032!\020'\032\0020\035*\0020\0352\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032\025\020(\032\0020\005*\0020\0022\006\020)\032\0020\037H\b\032)\020*\032\0020\005*\0020\0022\006\020)\032\0020\0372\022\020+\032\016\022\004\022\0020\037\022\004\022\0020\0050\004H\b\032\034\020,\032\004\030\0010\005*\0020\0022\006\020)\032\0020\037H\b¢\006\002\020-\032!\020.\032\0020\002*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032!\020.\032\0020\035*\0020\0352\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\0326\020/\032\0020\002*\0020\0022'\020\003\032#\022\023\022\0210\037¢\006\f\b1\022\b\b2\022\004\b\b()\022\004\022\0020\005\022\004\022\0020\00100H\b\0326\020/\032\0020\035*\0020\0352'\020\003\032#\022\023\022\0210\037¢\006\f\b1\022\b\b2\022\004\b\b()\022\004\022\0020\005\022\004\022\0020\00100H\b\032Q\0203\032\002H4\"\f\b\000\0204*\00605j\002`6*\0020\0022\006\020\027\032\002H42'\020\003\032#\022\023\022\0210\037¢\006\f\b1\022\b\b2\022\004\b\b()\022\004\022\0020\005\022\004\022\0020\00100H\b¢\006\002\0207\032!\0208\032\0020\002*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032!\0208\032\0020\035*\0020\0352\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032<\0209\032\002H4\"\f\b\000\0204*\00605j\002`6*\0020\0022\006\020\027\032\002H42\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b¢\006\002\020:\032<\020;\032\002H4\"\f\b\000\0204*\00605j\002`6*\0020\0022\006\020\027\032\002H42\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b¢\006\002\020:\032(\020<\032\004\030\0010\005*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b¢\006\002\020=\032(\020>\032\004\030\0010\005*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b¢\006\002\020=\032\n\020?\032\0020\005*\0020\002\032!\020?\032\0020\005*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032\021\020@\032\004\030\0010\005*\0020\002¢\006\002\020A\032(\020@\032\004\030\0010\005*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b¢\006\002\020=\0323\020B\032\b\022\004\022\002H 0\034\"\004\b\000\020 *\0020\0022\030\020\017\032\024\022\004\022\0020\005\022\n\022\b\022\004\022\002H 0\b0\004H\b\032L\020C\032\002H4\"\004\b\000\020 \"\020\b\001\0204*\n\022\006\b\000\022\002H 0D*\0020\0022\006\020\027\032\002H42\030\020\017\032\024\022\004\022\0020\005\022\n\022\b\022\004\022\002H 0\b0\004H\b¢\006\002\020E\032I\020F\032\002H \"\004\b\000\020 *\0020\0022\006\020G\032\002H 2'\020H\032#\022\023\022\021H ¢\006\f\b1\022\b\b2\022\004\b\b(I\022\004\022\0020\005\022\004\022\002H 00H\b¢\006\002\020J\032^\020K\032\002H \"\004\b\000\020 *\0020\0022\006\020G\032\002H 2<\020H\0328\022\023\022\0210\037¢\006\f\b1\022\b\b2\022\004\b\b()\022\023\022\021H ¢\006\f\b1\022\b\b2\022\004\b\b(I\022\004\022\0020\005\022\004\022\002H 0LH\b¢\006\002\020M\032I\020N\032\002H \"\004\b\000\020 *\0020\0022\006\020G\032\002H 2'\020H\032#\022\004\022\0020\005\022\023\022\021H ¢\006\f\b1\022\b\b2\022\004\b\b(I\022\004\022\002H 00H\b¢\006\002\020J\032^\020O\032\002H \"\004\b\000\020 *\0020\0022\006\020G\032\002H 2<\020H\0328\022\023\022\0210\037¢\006\f\b1\022\b\b2\022\004\b\b()\022\004\022\0020\005\022\023\022\021H ¢\006\f\b1\022\b\b2\022\004\b\b(I\022\004\022\002H 0LH\b¢\006\002\020M\032!\020P\032\0020Q*\0020\0022\022\020R\032\016\022\004\022\0020\005\022\004\022\0020Q0\004H\b\0326\020S\032\0020Q*\0020\0022'\020R\032#\022\023\022\0210\037¢\006\f\b1\022\b\b2\022\004\b\b()\022\004\022\0020\005\022\004\022\0020Q00H\b\032)\020T\032\0020\005*\0020\0022\006\020)\032\0020\0372\022\020+\032\016\022\004\022\0020\037\022\004\022\0020\0050\004H\b\032\031\020U\032\004\030\0010\005*\0020\0022\006\020)\032\0020\037¢\006\002\020-\0329\020V\032\024\022\004\022\002H\r\022\n\022\b\022\004\022\0020\0050\0340\f\"\004\b\000\020\r*\0020\0022\022\020\022\032\016\022\004\022\0020\005\022\004\022\002H\r0\004H\b\032S\020V\032\024\022\004\022\002H\r\022\n\022\b\022\004\022\002H\0160\0340\f\"\004\b\000\020\r\"\004\b\001\020\016*\0020\0022\022\020\022\032\016\022\004\022\0020\005\022\004\022\002H\r0\0042\022\020\023\032\016\022\004\022\0020\005\022\004\022\002H\0160\004H\b\032R\020W\032\002H\025\"\004\b\000\020\r\"\034\b\001\020\025*\026\022\006\b\000\022\002H\r\022\n\022\b\022\004\022\0020\0050X0\026*\0020\0022\006\020\027\032\002H\0252\022\020\022\032\016\022\004\022\0020\005\022\004\022\002H\r0\004H\b¢\006\002\020\030\032l\020W\032\002H\025\"\004\b\000\020\r\"\004\b\001\020\016\"\034\b\002\020\025*\026\022\006\b\000\022\002H\r\022\n\022\b\022\004\022\002H\0160X0\026*\0020\0022\006\020\027\032\002H\0252\022\020\022\032\016\022\004\022\0020\005\022\004\022\002H\r0\0042\022\020\023\032\016\022\004\022\0020\005\022\004\022\002H\0160\004H\b¢\006\002\020\031\0325\020Y\032\016\022\004\022\0020\005\022\004\022\002H\r0Z\"\004\b\000\020\r*\0020\0022\024\b\004\020\022\032\016\022\004\022\0020\005\022\004\022\002H\r0\004H\b\032!\020[\032\0020\037*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032!\020\\\032\0020\037*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032\n\020]\032\0020\005*\0020\002\032!\020]\032\0020\005*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032\021\020^\032\004\030\0010\005*\0020\002¢\006\002\020A\032(\020^\032\004\030\0010\005*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b¢\006\002\020=\032-\020_\032\b\022\004\022\002H 0\034\"\004\b\000\020 *\0020\0022\022\020\017\032\016\022\004\022\0020\005\022\004\022\002H 0\004H\b\032B\020`\032\b\022\004\022\002H 0\034\"\004\b\000\020 *\0020\0022'\020\017\032#\022\023\022\0210\037¢\006\f\b1\022\b\b2\022\004\b\b()\022\004\022\0020\005\022\004\022\002H 00H\b\032H\020a\032\b\022\004\022\002H 0\034\"\b\b\000\020 *\0020b*\0020\0022)\020\017\032%\022\023\022\0210\037¢\006\f\b1\022\b\b2\022\004\b\b()\022\004\022\0020\005\022\006\022\004\030\001H 00H\b\032a\020c\032\002H4\"\b\b\000\020 *\0020b\"\020\b\001\0204*\n\022\006\b\000\022\002H 0D*\0020\0022\006\020\027\032\002H42)\020\017\032%\022\023\022\0210\037¢\006\f\b1\022\b\b2\022\004\b\b()\022\004\022\0020\005\022\006\022\004\030\001H 00H\b¢\006\002\020d\032[\020e\032\002H4\"\004\b\000\020 \"\020\b\001\0204*\n\022\006\b\000\022\002H 0D*\0020\0022\006\020\027\032\002H42'\020\017\032#\022\023\022\0210\037¢\006\f\b1\022\b\b2\022\004\b\b()\022\004\022\0020\005\022\004\022\002H 00H\b¢\006\002\020d\0323\020f\032\b\022\004\022\002H 0\034\"\b\b\000\020 *\0020b*\0020\0022\024\020\017\032\020\022\004\022\0020\005\022\006\022\004\030\001H 0\004H\b\032L\020g\032\002H4\"\b\b\000\020 *\0020b\"\020\b\001\0204*\n\022\006\b\000\022\002H 0D*\0020\0022\006\020\027\032\002H42\024\020\017\032\020\022\004\022\0020\005\022\006\022\004\030\001H 0\004H\b¢\006\002\020E\032F\020h\032\002H4\"\004\b\000\020 \"\020\b\001\0204*\n\022\006\b\000\022\002H 0D*\0020\0022\006\020\027\032\002H42\022\020\017\032\016\022\004\022\0020\005\022\004\022\002H 0\004H\b¢\006\002\020E\032\021\020i\032\004\030\0010\005*\0020\002¢\006\002\020A\0328\020j\032\004\030\0010\005\"\016\b\000\020 *\b\022\004\022\002H 0k*\0020\0022\022\020l\032\016\022\004\022\0020\005\022\004\022\002H 0\004H\b¢\006\002\020=\032-\020m\032\004\030\0010\005*\0020\0022\032\020n\032\026\022\006\b\000\022\0020\0050oj\n\022\006\b\000\022\0020\005`p¢\006\002\020q\032\021\020r\032\004\030\0010\005*\0020\002¢\006\002\020A\0328\020s\032\004\030\0010\005\"\016\b\000\020 *\b\022\004\022\002H 0k*\0020\0022\022\020l\032\016\022\004\022\0020\005\022\004\022\002H 0\004H\b¢\006\002\020=\032-\020t\032\004\030\0010\005*\0020\0022\032\020n\032\026\022\006\b\000\022\0020\0050oj\n\022\006\b\000\022\0020\005`p¢\006\002\020q\032\n\020u\032\0020\001*\0020\002\032!\020u\032\0020\001*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\0320\020v\032\002Hw\"\b\b\000\020w*\0020\002*\002Hw2\022\020R\032\016\022\004\022\0020\005\022\004\022\0020Q0\004H\b¢\006\002\020x\032-\020y\032\016\022\004\022\0020\002\022\004\022\0020\0020\020*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032-\020y\032\016\022\004\022\0020\035\022\004\022\0020\0350\020*\0020\0352\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\0326\020z\032\0020\005*\0020\0022'\020H\032#\022\023\022\0210\005¢\006\f\b1\022\b\b2\022\004\b\b(I\022\004\022\0020\005\022\004\022\0020\00500H\b\032K\020{\032\0020\005*\0020\0022<\020H\0328\022\023\022\0210\037¢\006\f\b1\022\b\b2\022\004\b\b()\022\023\022\0210\005¢\006\f\b1\022\b\b2\022\004\b\b(I\022\004\022\0020\005\022\004\022\0020\0050LH\b\0326\020|\032\0020\005*\0020\0022'\020H\032#\022\004\022\0020\005\022\023\022\0210\005¢\006\f\b1\022\b\b2\022\004\b\b(I\022\004\022\0020\00500H\b\032K\020}\032\0020\005*\0020\0022<\020H\0328\022\023\022\0210\037¢\006\f\b1\022\b\b2\022\004\b\b()\022\004\022\0020\005\022\023\022\0210\005¢\006\f\b1\022\b\b2\022\004\b\b(I\022\004\022\0020\0050LH\b\032\n\020~\032\0020\002*\0020\002\032\r\020~\032\0020\035*\0020\035H\b\032\n\020\032\0020\005*\0020\002\032!\020\032\0020\005*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032\022\020\001\032\004\030\0010\005*\0020\002¢\006\002\020A\032)\020\001\032\004\030\0010\005*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b¢\006\002\020=\032\032\020\001\032\0020\002*\0020\0022\r\020\001\032\b\022\004\022\0020\0370\b\032\025\020\001\032\0020\002*\0020\0022\b\020\001\032\0030\001\032\035\020\001\032\0020\035*\0020\0352\r\020\001\032\b\022\004\022\0020\0370\bH\b\032\025\020\001\032\0020\035*\0020\0352\b\020\001\032\0030\001\032\"\020\001\032\0020\037*\0020\0022\022\020l\032\016\022\004\022\0020\005\022\004\022\0020\0370\004H\b\032$\020\001\032\0030\001*\0020\0022\023\020l\032\017\022\004\022\0020\005\022\005\022\0030\0010\004H\b\032\023\020\001\032\0020\002*\0020\0022\006\020$\032\0020\037\032\023\020\001\032\0020\035*\0020\0352\006\020$\032\0020\037\032\023\020\001\032\0020\002*\0020\0022\006\020$\032\0020\037\032\023\020\001\032\0020\035*\0020\0352\006\020$\032\0020\037\032\"\020\001\032\0020\002*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032\"\020\001\032\0020\035*\0020\0352\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032\"\020\001\032\0020\002*\0020\0022\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032\"\020\001\032\0020\035*\0020\0352\022\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0010\004H\b\032+\020\001\032\002H4\"\020\b\000\0204*\n\022\006\b\000\022\0020\0050D*\0020\0022\006\020\027\032\002H4¢\006\003\020\001\032\035\020\001\032\024\022\004\022\0020\0050\001j\t\022\004\022\0020\005`\001*\0020\002\032\021\020\001\032\b\022\004\022\0020\0050\034*\0020\002\032\021\020\001\032\b\022\004\022\0020\0050X*\0020\002\032\022\020\001\032\t\022\004\022\0020\0050\001*\0020\002\032\035\020\001\032\024\022\004\022\0020\0050\001j\t\022\004\022\0020\005`\001*\0020\002\0321\020\001\032\b\022\004\022\0020\0350\034*\0020\0022\006\020\036\032\0020\0372\t\b\002\020\001\032\0020\0372\t\b\002\020\001\032\0020\001H\007\032K\020\001\032\b\022\004\022\002H 0\034\"\004\b\000\020 *\0020\0022\006\020\036\032\0020\0372\t\b\002\020\001\032\0020\0372\t\b\002\020\001\032\0020\0012\022\020\017\032\016\022\004\022\0020\002\022\004\022\002H 0\004H\007\0321\020\001\032\b\022\004\022\0020\0350\n*\0020\0022\006\020\036\032\0020\0372\t\b\002\020\001\032\0020\0372\t\b\002\020\001\032\0020\001H\007\032K\020\001\032\b\022\004\022\002H 0\n\"\004\b\000\020 *\0020\0022\006\020\036\032\0020\0372\t\b\002\020\001\032\0020\0372\t\b\002\020\001\032\0020\0012\022\020\017\032\016\022\004\022\0020\002\022\004\022\002H 0\004H\007\032\030\020\001\032\017\022\013\022\t\022\004\022\0020\0050\0010\b*\0020\002\032)\020\001\032\024\022\020\022\016\022\004\022\0020\005\022\004\022\0020\0050\0200\034*\0020\0022\007\020\001\032\0020\002H\004\032]\020\001\032\b\022\004\022\002H\0160\034\"\004\b\000\020\016*\0020\0022\007\020\001\032\0020\00228\020\017\0324\022\024\022\0220\005¢\006\r\b1\022\t\b2\022\005\b\b(\001\022\024\022\0220\005¢\006\r\b1\022\t\b2\022\005\b\b( \001\022\004\022\002H\01600H\b\032\037\020¡\001\032\024\022\020\022\016\022\004\022\0020\005\022\004\022\0020\0050\0200\034*\0020\002H\007\032T\020¡\001\032\b\022\004\022\002H 0\034\"\004\b\000\020 *\0020\00228\020\017\0324\022\024\022\0220\005¢\006\r\b1\022\t\b2\022\005\b\b(\001\022\024\022\0220\005¢\006\r\b1\022\t\b2\022\005\b\b( \001\022\004\022\002H 00H\b¨\006¢\001"}, d2={"all", "", "", "predicate", "Lkotlin/Function1;", "", "any", "asIterable", "", "asSequence", "Lkotlin/sequences/Sequence;", "associate", "", "K", "V", "transform", "Lkotlin/Pair;", "associateBy", "keySelector", "valueTransform", "associateByTo", "M", "", "destination", "(Ljava/lang/CharSequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "(Ljava/lang/CharSequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "associateTo", "chunked", "", "", "size", "", "R", "chunkedSequence", "count", "drop", "n", "dropLast", "dropLastWhile", "dropWhile", "elementAt", "index", "elementAtOrElse", "defaultValue", "elementAtOrNull", "(Ljava/lang/CharSequence;I)Ljava/lang/Character;", "filter", "filterIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "filterIndexedTo", "C", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "(Ljava/lang/CharSequence;Ljava/lang/Appendable;Lkotlin/jvm/functions/Function2;)Ljava/lang/Appendable;", "filterNot", "filterNotTo", "(Ljava/lang/CharSequence;Ljava/lang/Appendable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Appendable;", "filterTo", "find", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Character;", "findLast", "first", "firstOrNull", "(Ljava/lang/CharSequence;)Ljava/lang/Character;", "flatMap", "flatMapTo", "", "(Ljava/lang/CharSequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "fold", "initial", "operation", "acc", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "foldIndexed", "Lkotlin/Function3;", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "foldRight", "foldRightIndexed", "forEach", "", "action", "forEachIndexed", "getOrElse", "getOrNull", "groupBy", "groupByTo", "", "groupingBy", "Lkotlin/collections/Grouping;", "indexOfFirst", "indexOfLast", "last", "lastOrNull", "map", "mapIndexed", "mapIndexedNotNull", "", "mapIndexedNotNullTo", "(Ljava/lang/CharSequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function2;)Ljava/util/Collection;", "mapIndexedTo", "mapNotNull", "mapNotNullTo", "mapTo", "max", "maxBy", "", "selector", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/CharSequence;Ljava/util/Comparator;)Ljava/lang/Character;", "min", "minBy", "minWith", "none", "onEach", "S", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/CharSequence;", "partition", "reduce", "reduceIndexed", "reduceRight", "reduceRightIndexed", "reversed", "single", "singleOrNull", "slice", "indices", "Lkotlin/ranges/IntRange;", "sumBy", "sumByDouble", "", "take", "takeLast", "takeLastWhile", "takeWhile", "toCollection", "(Ljava/lang/CharSequence;Ljava/util/Collection;)Ljava/util/Collection;", "toHashSet", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "toList", "toMutableList", "toSet", "", "toSortedSet", "Ljava/util/SortedSet;", "Lkotlin/collections/SortedSet;", "windowed", "step", "partialWindows", "windowedSequence", "withIndex", "Lkotlin/collections/IndexedValue;", "zip", "other", "a", "b", "zipWithNext", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/text/StringsKt")
class StringsKt___StringsKt
  extends StringsKt__StringsKt
{
  public StringsKt___StringsKt() {}
  
  public static final boolean all(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      if (!((Boolean)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)))).booleanValue()) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static final boolean any(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int i;
    if (paramCharSequence.length() == 0) {
      i = 1;
    } else {
      i = 0;
    }
    return i ^ 0x1;
  }
  
  public static final boolean any(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      if (((Boolean)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)))).booleanValue()) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  @NotNull
  public static final Iterable<Character> asIterable(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    if ((paramCharSequence instanceof String))
    {
      int i;
      if (paramCharSequence.length() == 0) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        return (Iterable)CollectionsKt.emptyList();
      }
    }
    (Iterable)new Iterable()
    {
      @NotNull
      public Iterator<Character> iterator()
      {
        return (Iterator)StringsKt.iterator(receiver$0$inlined);
      }
    };
  }
  
  @NotNull
  public static final Sequence<Character> asSequence(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    if ((paramCharSequence instanceof String))
    {
      int i;
      if (paramCharSequence.length() == 0) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        return SequencesKt.emptySequence();
      }
    }
    (Sequence)new Sequence()
    {
      @NotNull
      public Iterator<Character> iterator()
      {
        return (Iterator)StringsKt.iterator(receiver$0$inlined);
      }
    };
  }
  
  @NotNull
  public static final <K, V> Map<K, V> associate(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    Map localMap = (Map)new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(paramCharSequence.length()), 16));
    int i = 0;
    while (i < paramCharSequence.length())
    {
      Pair localPair = (Pair)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)));
      localMap.put(localPair.getFirst(), localPair.getSecond());
      i += 1;
    }
    return localMap;
  }
  
  @NotNull
  public static final <K> Map<K, Character> associateBy(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, ? extends K> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    Map localMap = (Map)new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(paramCharSequence.length()), 16));
    int i = 0;
    while (i < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(i);
      localMap.put(paramFunction1.invoke(Character.valueOf(c)), Character.valueOf(c));
      i += 1;
    }
    return localMap;
  }
  
  @NotNull
  public static final <K, V> Map<K, V> associateBy(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, ? extends K> paramFunction1, @NotNull Function1<? super Character, ? extends V> paramFunction11)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    Intrinsics.checkParameterIsNotNull(paramFunction11, "valueTransform");
    Map localMap = (Map)new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(paramCharSequence.length()), 16));
    int i = 0;
    while (i < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(i);
      localMap.put(paramFunction1.invoke(Character.valueOf(c)), paramFunction11.invoke(Character.valueOf(c)));
      i += 1;
    }
    return localMap;
  }
  
  @NotNull
  public static final <K, M extends Map<? super K, ? super Character>> M associateByTo(@NotNull CharSequence paramCharSequence, @NotNull M paramM, @NotNull Function1<? super Character, ? extends K> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(i);
      paramM.put(paramFunction1.invoke(Character.valueOf(c)), Character.valueOf(c));
      i += 1;
    }
    return paramM;
  }
  
  @NotNull
  public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull CharSequence paramCharSequence, @NotNull M paramM, @NotNull Function1<? super Character, ? extends K> paramFunction1, @NotNull Function1<? super Character, ? extends V> paramFunction11)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    Intrinsics.checkParameterIsNotNull(paramFunction11, "valueTransform");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(i);
      paramM.put(paramFunction1.invoke(Character.valueOf(c)), paramFunction11.invoke(Character.valueOf(c)));
      i += 1;
    }
    return paramM;
  }
  
  @NotNull
  public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull CharSequence paramCharSequence, @NotNull M paramM, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      Pair localPair = (Pair)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)));
      paramM.put(localPair.getFirst(), localPair.getSecond());
      i += 1;
    }
    return paramM;
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final List<String> chunked(@NotNull CharSequence paramCharSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    return StringsKt.windowed(paramCharSequence, paramInt, paramInt, true);
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final <R> List<R> chunked(@NotNull CharSequence paramCharSequence, int paramInt, @NotNull Function1<? super CharSequence, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    return StringsKt.windowed(paramCharSequence, paramInt, paramInt, true, paramFunction1);
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final Sequence<String> chunkedSequence(@NotNull CharSequence paramCharSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    return StringsKt.chunkedSequence(paramCharSequence, paramInt, (Function1)chunkedSequence.1.INSTANCE);
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final <R> Sequence<R> chunkedSequence(@NotNull CharSequence paramCharSequence, int paramInt, @NotNull Function1<? super CharSequence, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    return StringsKt.windowedSequence(paramCharSequence, paramInt, paramInt, true, paramFunction1);
  }
  
  @InlineOnly
  private static final int count(@NotNull CharSequence paramCharSequence)
  {
    return paramCharSequence.length();
  }
  
  public static final int count(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int i = 0;
    int k;
    for (int j = 0; i < paramCharSequence.length(); j = k)
    {
      k = j;
      if (((Boolean)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)))).booleanValue()) {
        k = j + 1;
      }
      i += 1;
    }
    return j;
  }
  
  @NotNull
  public static final CharSequence drop(@NotNull CharSequence paramCharSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int i;
    if (paramInt >= 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      paramCharSequence = new StringBuilder();
      paramCharSequence.append("Requested character count ");
      paramCharSequence.append(paramInt);
      paramCharSequence.append(" is less than zero.");
      throw ((Throwable)new IllegalArgumentException(paramCharSequence.toString().toString()));
    }
    return paramCharSequence.subSequence(RangesKt.coerceAtMost(paramInt, paramCharSequence.length()), paramCharSequence.length());
  }
  
  @NotNull
  public static final String drop(@NotNull String paramString, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    int i;
    if (paramInt >= 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      paramString = new StringBuilder();
      paramString.append("Requested character count ");
      paramString.append(paramInt);
      paramString.append(" is less than zero.");
      throw ((Throwable)new IllegalArgumentException(paramString.toString().toString()));
    }
    paramString = paramString.substring(RangesKt.coerceAtMost(paramInt, paramString.length()));
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).substring(startIndex)");
    return paramString;
  }
  
  @NotNull
  public static final CharSequence dropLast(@NotNull CharSequence paramCharSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int i;
    if (paramInt >= 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      paramCharSequence = new StringBuilder();
      paramCharSequence.append("Requested character count ");
      paramCharSequence.append(paramInt);
      paramCharSequence.append(" is less than zero.");
      throw ((Throwable)new IllegalArgumentException(paramCharSequence.toString().toString()));
    }
    return StringsKt.take(paramCharSequence, RangesKt.coerceAtLeast(paramCharSequence.length() - paramInt, 0));
  }
  
  @NotNull
  public static final String dropLast(@NotNull String paramString, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    int i;
    if (paramInt >= 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      paramString = new StringBuilder();
      paramString.append("Requested character count ");
      paramString.append(paramInt);
      paramString.append(" is less than zero.");
      throw ((Throwable)new IllegalArgumentException(paramString.toString().toString()));
    }
    return StringsKt.take(paramString, RangesKt.coerceAtLeast(paramString.length() - paramInt, 0));
  }
  
  @NotNull
  public static final CharSequence dropLastWhile(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int i = StringsKt.getLastIndex(paramCharSequence);
    while (i >= 0)
    {
      if (!((Boolean)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)))).booleanValue()) {
        return paramCharSequence.subSequence(0, i + 1);
      }
      i -= 1;
    }
    return (CharSequence)"";
  }
  
  @NotNull
  public static final String dropLastWhile(@NotNull String paramString, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int i = StringsKt.getLastIndex((CharSequence)paramString);
    while (i >= 0)
    {
      if (!((Boolean)paramFunction1.invoke(Character.valueOf(paramString.charAt(i)))).booleanValue())
      {
        paramString = paramString.substring(0, i + 1);
        Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return paramString;
      }
      i -= 1;
    }
    return "";
  }
  
  @NotNull
  public static final CharSequence dropWhile(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int j = paramCharSequence.length();
    int i = 0;
    while (i < j)
    {
      if (!((Boolean)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)))).booleanValue()) {
        return paramCharSequence.subSequence(i, paramCharSequence.length());
      }
      i += 1;
    }
    return (CharSequence)"";
  }
  
  @NotNull
  public static final String dropWhile(@NotNull String paramString, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int j = ((CharSequence)paramString).length();
    int i = 0;
    while (i < j)
    {
      if (!((Boolean)paramFunction1.invoke(Character.valueOf(paramString.charAt(i)))).booleanValue())
      {
        paramString = paramString.substring(i);
        Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).substring(startIndex)");
        return paramString;
      }
      i += 1;
    }
    return "";
  }
  
  @InlineOnly
  private static final char elementAt(@NotNull CharSequence paramCharSequence, int paramInt)
  {
    return paramCharSequence.charAt(paramInt);
  }
  
  @InlineOnly
  private static final char elementAtOrElse(@NotNull CharSequence paramCharSequence, int paramInt, Function1<? super Integer, Character> paramFunction1)
  {
    if ((paramInt >= 0) && (paramInt <= StringsKt.getLastIndex(paramCharSequence))) {
      return paramCharSequence.charAt(paramInt);
    }
    return ((Character)paramFunction1.invoke(Integer.valueOf(paramInt))).charValue();
  }
  
  @InlineOnly
  private static final Character elementAtOrNull(@NotNull CharSequence paramCharSequence, int paramInt)
  {
    return StringsKt.getOrNull(paramCharSequence, paramInt);
  }
  
  @NotNull
  public static final CharSequence filter(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    Appendable localAppendable = (Appendable)new StringBuilder();
    int j = paramCharSequence.length();
    int i = 0;
    while (i < j)
    {
      char c = paramCharSequence.charAt(i);
      if (((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue()) {
        localAppendable.append(c);
      }
      i += 1;
    }
    return (CharSequence)localAppendable;
  }
  
  @NotNull
  public static final String filter(@NotNull String paramString, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramString = (CharSequence)paramString;
    Appendable localAppendable = (Appendable)new StringBuilder();
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      char c = paramString.charAt(i);
      if (((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue()) {
        localAppendable.append(c);
      }
      i += 1;
    }
    paramString = ((StringBuilder)localAppendable).toString();
    Intrinsics.checkExpressionValueIsNotNull(paramString, "filterTo(StringBuilder(), predicate).toString()");
    return paramString;
  }
  
  @NotNull
  public static final CharSequence filterIndexed(@NotNull CharSequence paramCharSequence, @NotNull Function2<? super Integer, ? super Character, Boolean> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "predicate");
    Appendable localAppendable = (Appendable)new StringBuilder();
    int j = 0;
    int i = 0;
    while (j < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(j);
      if (((Boolean)paramFunction2.invoke(Integer.valueOf(i), Character.valueOf(c))).booleanValue()) {
        localAppendable.append(c);
      }
      j += 1;
      i += 1;
    }
    return (CharSequence)localAppendable;
  }
  
  @NotNull
  public static final String filterIndexed(@NotNull String paramString, @NotNull Function2<? super Integer, ? super Character, Boolean> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "predicate");
    paramString = (CharSequence)paramString;
    Appendable localAppendable = (Appendable)new StringBuilder();
    int j = 0;
    int i = 0;
    while (j < paramString.length())
    {
      char c = paramString.charAt(j);
      if (((Boolean)paramFunction2.invoke(Integer.valueOf(i), Character.valueOf(c))).booleanValue()) {
        localAppendable.append(c);
      }
      j += 1;
      i += 1;
    }
    paramString = ((StringBuilder)localAppendable).toString();
    Intrinsics.checkExpressionValueIsNotNull(paramString, "filterIndexedTo(StringBu…(), predicate).toString()");
    return paramString;
  }
  
  @NotNull
  public static final <C extends Appendable> C filterIndexedTo(@NotNull CharSequence paramCharSequence, @NotNull C paramC, @NotNull Function2<? super Integer, ? super Character, Boolean> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "predicate");
    int j = 0;
    int i = 0;
    while (j < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(j);
      if (((Boolean)paramFunction2.invoke(Integer.valueOf(i), Character.valueOf(c))).booleanValue()) {
        paramC.append(c);
      }
      j += 1;
      i += 1;
    }
    return paramC;
  }
  
  @NotNull
  public static final CharSequence filterNot(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    Appendable localAppendable = (Appendable)new StringBuilder();
    int i = 0;
    while (i < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(i);
      if (!((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue()) {
        localAppendable.append(c);
      }
      i += 1;
    }
    return (CharSequence)localAppendable;
  }
  
  @NotNull
  public static final String filterNot(@NotNull String paramString, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramString = (CharSequence)paramString;
    Appendable localAppendable = (Appendable)new StringBuilder();
    int i = 0;
    while (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if (!((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue()) {
        localAppendable.append(c);
      }
      i += 1;
    }
    paramString = ((StringBuilder)localAppendable).toString();
    Intrinsics.checkExpressionValueIsNotNull(paramString, "filterNotTo(StringBuilder(), predicate).toString()");
    return paramString;
  }
  
  @NotNull
  public static final <C extends Appendable> C filterNotTo(@NotNull CharSequence paramCharSequence, @NotNull C paramC, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(i);
      if (!((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue()) {
        paramC.append(c);
      }
      i += 1;
    }
    return paramC;
  }
  
  @NotNull
  public static final <C extends Appendable> C filterTo(@NotNull CharSequence paramCharSequence, @NotNull C paramC, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int j = paramCharSequence.length();
    int i = 0;
    while (i < j)
    {
      char c = paramCharSequence.charAt(i);
      if (((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue()) {
        paramC.append(c);
      }
      i += 1;
    }
    return paramC;
  }
  
  @InlineOnly
  private static final Character find(@NotNull CharSequence paramCharSequence, Function1<? super Character, Boolean> paramFunction1)
  {
    int i = 0;
    while (i < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(i);
      if (((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue()) {
        return Character.valueOf(c);
      }
      i += 1;
    }
    return null;
  }
  
  @InlineOnly
  private static final Character findLast(@NotNull CharSequence paramCharSequence, Function1<? super Character, Boolean> paramFunction1)
  {
    int i = paramCharSequence.length();
    char c;
    do
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      c = paramCharSequence.charAt(i);
    } while (!((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue());
    return Character.valueOf(c);
    return null;
  }
  
  public static final char first(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int i;
    if (paramCharSequence.length() == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      throw ((Throwable)new NoSuchElementException("Char sequence is empty."));
    }
    return paramCharSequence.charAt(0);
  }
  
  public static final char first(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(i);
      if (((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue()) {
        return c;
      }
      i += 1;
    }
    throw ((Throwable)new NoSuchElementException("Char sequence contains no character matching the predicate."));
  }
  
  @Nullable
  public static final Character firstOrNull(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int i;
    if (paramCharSequence.length() == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      return null;
    }
    return Character.valueOf(paramCharSequence.charAt(0));
  }
  
  @Nullable
  public static final Character firstOrNull(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(i);
      if (((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue()) {
        return Character.valueOf(c);
      }
      i += 1;
    }
    return null;
  }
  
  @NotNull
  public static final <R> List<R> flatMap(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    Collection localCollection = (Collection)new ArrayList();
    int i = 0;
    while (i < paramCharSequence.length())
    {
      CollectionsKt.addAll(localCollection, (Iterable)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i))));
      i += 1;
    }
    return (List)localCollection;
  }
  
  @NotNull
  public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull CharSequence paramCharSequence, @NotNull C paramC, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      CollectionsKt.addAll(paramC, (Iterable)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i))));
      i += 1;
    }
    return paramC;
  }
  
  public static final <R> R fold(@NotNull CharSequence paramCharSequence, R paramR, @NotNull Function2<? super R, ? super Character, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      paramR = paramFunction2.invoke(paramR, Character.valueOf(paramCharSequence.charAt(i)));
      i += 1;
    }
    return paramR;
  }
  
  public static final <R> R foldIndexed(@NotNull CharSequence paramCharSequence, R paramR, @NotNull Function3<? super Integer, ? super R, ? super Character, ? extends R> paramFunction3)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction3, "operation");
    int j = 0;
    int i = 0;
    for (;;)
    {
      int k = i;
      if (j >= paramCharSequence.length()) {
        break;
      }
      char c = paramCharSequence.charAt(j);
      i = k + 1;
      paramR = paramFunction3.invoke(Integer.valueOf(k), paramR, Character.valueOf(c));
      j += 1;
    }
    return paramR;
  }
  
  public static final <R> R foldRight(@NotNull CharSequence paramCharSequence, R paramR, @NotNull Function2<? super Character, ? super R, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
    int i = StringsKt.getLastIndex(paramCharSequence);
    while (i >= 0)
    {
      paramR = paramFunction2.invoke(Character.valueOf(paramCharSequence.charAt(i)), paramR);
      i -= 1;
    }
    return paramR;
  }
  
  public static final <R> R foldRightIndexed(@NotNull CharSequence paramCharSequence, R paramR, @NotNull Function3<? super Integer, ? super Character, ? super R, ? extends R> paramFunction3)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction3, "operation");
    int i = StringsKt.getLastIndex(paramCharSequence);
    while (i >= 0)
    {
      paramR = paramFunction3.invoke(Integer.valueOf(i), Character.valueOf(paramCharSequence.charAt(i)), paramR);
      i -= 1;
    }
    return paramR;
  }
  
  public static final void forEach(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Unit> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "action");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)));
      i += 1;
    }
  }
  
  public static final void forEachIndexed(@NotNull CharSequence paramCharSequence, @NotNull Function2<? super Integer, ? super Character, Unit> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "action");
    int j = 0;
    int i = 0;
    for (;;)
    {
      int k = i;
      if (j >= paramCharSequence.length()) {
        break;
      }
      char c = paramCharSequence.charAt(j);
      i = k + 1;
      paramFunction2.invoke(Integer.valueOf(k), Character.valueOf(c));
      j += 1;
    }
  }
  
  @InlineOnly
  private static final char getOrElse(@NotNull CharSequence paramCharSequence, int paramInt, Function1<? super Integer, Character> paramFunction1)
  {
    if ((paramInt >= 0) && (paramInt <= StringsKt.getLastIndex(paramCharSequence))) {
      return paramCharSequence.charAt(paramInt);
    }
    return ((Character)paramFunction1.invoke(Integer.valueOf(paramInt))).charValue();
  }
  
  @Nullable
  public static final Character getOrNull(@NotNull CharSequence paramCharSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    if ((paramInt >= 0) && (paramInt <= StringsKt.getLastIndex(paramCharSequence))) {
      return Character.valueOf(paramCharSequence.charAt(paramInt));
    }
    return null;
  }
  
  @NotNull
  public static final <K> Map<K, List<Character>> groupBy(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, ? extends K> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    Map localMap = (Map)new LinkedHashMap();
    int i = 0;
    while (i < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(i);
      Object localObject3 = paramFunction1.invoke(Character.valueOf(c));
      Object localObject2 = localMap.get(localObject3);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new ArrayList();
        localMap.put(localObject3, localObject1);
      }
      ((List)localObject1).add(Character.valueOf(c));
      i += 1;
    }
    return localMap;
  }
  
  @NotNull
  public static final <K, V> Map<K, List<V>> groupBy(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, ? extends K> paramFunction1, @NotNull Function1<? super Character, ? extends V> paramFunction11)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    Intrinsics.checkParameterIsNotNull(paramFunction11, "valueTransform");
    Map localMap = (Map)new LinkedHashMap();
    int i = 0;
    while (i < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(i);
      Object localObject3 = paramFunction1.invoke(Character.valueOf(c));
      Object localObject2 = localMap.get(localObject3);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new ArrayList();
        localMap.put(localObject3, localObject1);
      }
      ((List)localObject1).add(paramFunction11.invoke(Character.valueOf(c)));
      i += 1;
    }
    return localMap;
  }
  
  @NotNull
  public static final <K, M extends Map<? super K, List<Character>>> M groupByTo(@NotNull CharSequence paramCharSequence, @NotNull M paramM, @NotNull Function1<? super Character, ? extends K> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(i);
      Object localObject3 = paramFunction1.invoke(Character.valueOf(c));
      Object localObject2 = paramM.get(localObject3);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new ArrayList();
        paramM.put(localObject3, localObject1);
      }
      ((List)localObject1).add(Character.valueOf(c));
      i += 1;
    }
    return paramM;
  }
  
  @NotNull
  public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull CharSequence paramCharSequence, @NotNull M paramM, @NotNull Function1<? super Character, ? extends K> paramFunction1, @NotNull Function1<? super Character, ? extends V> paramFunction11)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    Intrinsics.checkParameterIsNotNull(paramFunction11, "valueTransform");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(i);
      Object localObject3 = paramFunction1.invoke(Character.valueOf(c));
      Object localObject2 = paramM.get(localObject3);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new ArrayList();
        paramM.put(localObject3, localObject1);
      }
      ((List)localObject1).add(paramFunction11.invoke(Character.valueOf(c)));
      i += 1;
    }
    return paramM;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <K> Grouping<Character, K> groupingBy(@NotNull CharSequence paramCharSequence, @NotNull final Function1<? super Character, ? extends K> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    (Grouping)new Grouping()
    {
      public K keyOf(char paramAnonymousChar)
      {
        return paramFunction1.invoke(Character.valueOf(paramAnonymousChar));
      }
      
      @NotNull
      public Iterator<Character> sourceIterator()
      {
        return (Iterator)StringsKt.iterator(receiver$0);
      }
    };
  }
  
  public static final int indexOfFirst(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int j = paramCharSequence.length();
    int i = 0;
    while (i < j)
    {
      if (((Boolean)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)))).booleanValue()) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public static final int indexOfLast(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int i = paramCharSequence.length() - 1;
    while (i >= 0)
    {
      if (((Boolean)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)))).booleanValue()) {
        return i;
      }
      i -= 1;
    }
    return -1;
  }
  
  public static final char last(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int i;
    if (paramCharSequence.length() == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      throw ((Throwable)new NoSuchElementException("Char sequence is empty."));
    }
    return paramCharSequence.charAt(StringsKt.getLastIndex(paramCharSequence));
  }
  
  public static final char last(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int i = paramCharSequence.length();
    char c;
    do
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      c = paramCharSequence.charAt(i);
    } while (!((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue());
    return c;
    throw ((Throwable)new NoSuchElementException("Char sequence contains no character matching the predicate."));
  }
  
  @Nullable
  public static final Character lastOrNull(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int i;
    if (paramCharSequence.length() == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      return null;
    }
    return Character.valueOf(paramCharSequence.charAt(paramCharSequence.length() - 1));
  }
  
  @Nullable
  public static final Character lastOrNull(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int i = paramCharSequence.length();
    char c;
    do
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      c = paramCharSequence.charAt(i);
    } while (!((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue());
    return Character.valueOf(c);
    return null;
  }
  
  @NotNull
  public static final <R> List<R> map(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    Collection localCollection = (Collection)new ArrayList(paramCharSequence.length());
    int i = 0;
    while (i < paramCharSequence.length())
    {
      localCollection.add(paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i))));
      i += 1;
    }
    return (List)localCollection;
  }
  
  @NotNull
  public static final <R> List<R> mapIndexed(@NotNull CharSequence paramCharSequence, @NotNull Function2<? super Integer, ? super Character, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "transform");
    Collection localCollection = (Collection)new ArrayList(paramCharSequence.length());
    int j = 0;
    int i = 0;
    for (;;)
    {
      int k = i;
      if (j >= paramCharSequence.length()) {
        break;
      }
      char c = paramCharSequence.charAt(j);
      i = k + 1;
      localCollection.add(paramFunction2.invoke(Integer.valueOf(k), Character.valueOf(c)));
      j += 1;
    }
    return (List)localCollection;
  }
  
  @NotNull
  public static final <R> List<R> mapIndexedNotNull(@NotNull CharSequence paramCharSequence, @NotNull Function2<? super Integer, ? super Character, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "transform");
    Collection localCollection = (Collection)new ArrayList();
    int j = 0;
    int i = 0;
    while (j < paramCharSequence.length())
    {
      Object localObject = paramFunction2.invoke(Integer.valueOf(i), Character.valueOf(paramCharSequence.charAt(j)));
      if (localObject != null) {
        localCollection.add(localObject);
      }
      j += 1;
      i += 1;
    }
    return (List)localCollection;
  }
  
  @NotNull
  public static final <R, C extends Collection<? super R>> C mapIndexedNotNullTo(@NotNull CharSequence paramCharSequence, @NotNull C paramC, @NotNull Function2<? super Integer, ? super Character, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "transform");
    int j = 0;
    int i = 0;
    while (j < paramCharSequence.length())
    {
      Object localObject = paramFunction2.invoke(Integer.valueOf(i), Character.valueOf(paramCharSequence.charAt(j)));
      if (localObject != null) {
        paramC.add(localObject);
      }
      j += 1;
      i += 1;
    }
    return paramC;
  }
  
  @NotNull
  public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull CharSequence paramCharSequence, @NotNull C paramC, @NotNull Function2<? super Integer, ? super Character, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "transform");
    int j = 0;
    int i = 0;
    for (;;)
    {
      int k = i;
      if (j >= paramCharSequence.length()) {
        break;
      }
      char c = paramCharSequence.charAt(j);
      i = k + 1;
      paramC.add(paramFunction2.invoke(Integer.valueOf(k), Character.valueOf(c)));
      j += 1;
    }
    return paramC;
  }
  
  @NotNull
  public static final <R> List<R> mapNotNull(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    Collection localCollection = (Collection)new ArrayList();
    int i = 0;
    while (i < paramCharSequence.length())
    {
      Object localObject = paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)));
      if (localObject != null) {
        localCollection.add(localObject);
      }
      i += 1;
    }
    return (List)localCollection;
  }
  
  @NotNull
  public static final <R, C extends Collection<? super R>> C mapNotNullTo(@NotNull CharSequence paramCharSequence, @NotNull C paramC, @NotNull Function1<? super Character, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      Object localObject = paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)));
      if (localObject != null) {
        paramC.add(localObject);
      }
      i += 1;
    }
    return paramC;
  }
  
  @NotNull
  public static final <R, C extends Collection<? super R>> C mapTo(@NotNull CharSequence paramCharSequence, @NotNull C paramC, @NotNull Function1<? super Character, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      paramC.add(paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i))));
      i += 1;
    }
    return paramC;
  }
  
  @Nullable
  public static final Character max(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int i = paramCharSequence.length();
    int j = 1;
    if (i == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      return null;
    }
    char c1 = paramCharSequence.charAt(0);
    int k = StringsKt.getLastIndex(paramCharSequence);
    char c2 = c1;
    if (1 <= k)
    {
      i = j;
      for (c2 = c1;; c2 = c1)
      {
        char c3 = paramCharSequence.charAt(i);
        c1 = c2;
        if (c2 < c3) {
          c1 = c3;
        }
        c2 = c1;
        if (i == k) {
          break;
        }
        i += 1;
      }
    }
    return Character.valueOf(c2);
  }
  
  @Nullable
  public static final <R extends Comparable<? super R>> Character maxBy(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "selector");
    int i = paramCharSequence.length();
    int j = 1;
    if (i == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      return null;
    }
    char c1 = paramCharSequence.charAt(0);
    Object localObject1 = (Comparable)paramFunction1.invoke(Character.valueOf(c1));
    int k = StringsKt.getLastIndex(paramCharSequence);
    char c2 = c1;
    if (1 <= k)
    {
      i = j;
      for (;;)
      {
        c2 = paramCharSequence.charAt(i);
        Comparable localComparable = (Comparable)paramFunction1.invoke(Character.valueOf(c2));
        Object localObject2 = localObject1;
        if (((Comparable)localObject1).compareTo(localComparable) < 0)
        {
          c1 = c2;
          localObject2 = localComparable;
        }
        c2 = c1;
        if (i == k) {
          break;
        }
        i += 1;
        localObject1 = localObject2;
      }
    }
    return Character.valueOf(c2);
  }
  
  @Nullable
  public static final Character maxWith(@NotNull CharSequence paramCharSequence, @NotNull Comparator<? super Character> paramComparator)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramComparator, "comparator");
    int i = paramCharSequence.length();
    int j = 1;
    if (i == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      return null;
    }
    char c1 = paramCharSequence.charAt(0);
    int k = StringsKt.getLastIndex(paramCharSequence);
    char c2 = c1;
    if (1 <= k)
    {
      i = j;
      for (c2 = c1;; c2 = c1)
      {
        char c3 = paramCharSequence.charAt(i);
        c1 = c2;
        if (paramComparator.compare(Character.valueOf(c2), Character.valueOf(c3)) < 0) {
          c1 = c3;
        }
        c2 = c1;
        if (i == k) {
          break;
        }
        i += 1;
      }
    }
    return Character.valueOf(c2);
  }
  
  @Nullable
  public static final Character min(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int i = paramCharSequence.length();
    int j = 1;
    if (i == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      return null;
    }
    char c1 = paramCharSequence.charAt(0);
    int k = StringsKt.getLastIndex(paramCharSequence);
    char c2 = c1;
    if (1 <= k)
    {
      i = j;
      for (c2 = c1;; c2 = c1)
      {
        char c3 = paramCharSequence.charAt(i);
        c1 = c2;
        if (c2 > c3) {
          c1 = c3;
        }
        c2 = c1;
        if (i == k) {
          break;
        }
        i += 1;
      }
    }
    return Character.valueOf(c2);
  }
  
  @Nullable
  public static final <R extends Comparable<? super R>> Character minBy(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "selector");
    int i = paramCharSequence.length();
    int j = 1;
    if (i == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      return null;
    }
    char c1 = paramCharSequence.charAt(0);
    Object localObject1 = (Comparable)paramFunction1.invoke(Character.valueOf(c1));
    int k = StringsKt.getLastIndex(paramCharSequence);
    char c2 = c1;
    if (1 <= k)
    {
      i = j;
      for (;;)
      {
        c2 = paramCharSequence.charAt(i);
        Comparable localComparable = (Comparable)paramFunction1.invoke(Character.valueOf(c2));
        Object localObject2 = localObject1;
        if (((Comparable)localObject1).compareTo(localComparable) > 0)
        {
          c1 = c2;
          localObject2 = localComparable;
        }
        c2 = c1;
        if (i == k) {
          break;
        }
        i += 1;
        localObject1 = localObject2;
      }
    }
    return Character.valueOf(c2);
  }
  
  @Nullable
  public static final Character minWith(@NotNull CharSequence paramCharSequence, @NotNull Comparator<? super Character> paramComparator)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramComparator, "comparator");
    int i = paramCharSequence.length();
    int j = 1;
    if (i == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      return null;
    }
    char c1 = paramCharSequence.charAt(0);
    int k = StringsKt.getLastIndex(paramCharSequence);
    char c2 = c1;
    if (1 <= k)
    {
      i = j;
      for (c2 = c1;; c2 = c1)
      {
        char c3 = paramCharSequence.charAt(i);
        c1 = c2;
        if (paramComparator.compare(Character.valueOf(c2), Character.valueOf(c3)) > 0) {
          c1 = c3;
        }
        c2 = c1;
        if (i == k) {
          break;
        }
        i += 1;
      }
    }
    return Character.valueOf(c2);
  }
  
  public static final boolean none(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    return paramCharSequence.length() == 0;
  }
  
  public static final boolean none(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      if (((Boolean)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)))).booleanValue()) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <S extends CharSequence> S onEach(@NotNull S paramS, @NotNull Function1<? super Character, Unit> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramS, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "action");
    int i = 0;
    while (i < paramS.length())
    {
      paramFunction1.invoke(Character.valueOf(paramS.charAt(i)));
      i += 1;
    }
    return paramS;
  }
  
  @NotNull
  public static final Pair<CharSequence, CharSequence> partition(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    StringBuilder localStringBuilder1 = new StringBuilder();
    StringBuilder localStringBuilder2 = new StringBuilder();
    int i = 0;
    while (i < paramCharSequence.length())
    {
      char c = paramCharSequence.charAt(i);
      if (((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue()) {
        localStringBuilder1.append(c);
      } else {
        localStringBuilder2.append(c);
      }
      i += 1;
    }
    return new Pair(localStringBuilder1, localStringBuilder2);
  }
  
  @NotNull
  public static final Pair<String, String> partition(@NotNull String paramString, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    StringBuilder localStringBuilder1 = new StringBuilder();
    StringBuilder localStringBuilder2 = new StringBuilder();
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      char c = paramString.charAt(i);
      if (((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue()) {
        localStringBuilder1.append(c);
      } else {
        localStringBuilder2.append(c);
      }
      i += 1;
    }
    return new Pair(localStringBuilder1.toString(), localStringBuilder2.toString());
  }
  
  public static final char reduce(@NotNull CharSequence paramCharSequence, @NotNull Function2<? super Character, ? super Character, Character> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
    int i = paramCharSequence.length();
    int j = 1;
    if (i == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      throw ((Throwable)new UnsupportedOperationException("Empty char sequence can't be reduced."));
    }
    char c2 = paramCharSequence.charAt(0);
    int k = StringsKt.getLastIndex(paramCharSequence);
    char c1 = c2;
    if (1 <= k)
    {
      i = j;
      for (;;)
      {
        c2 = ((Character)paramFunction2.invoke(Character.valueOf(c2), Character.valueOf(paramCharSequence.charAt(i)))).charValue();
        c1 = c2;
        if (i == k) {
          break;
        }
        i += 1;
      }
    }
    return c1;
  }
  
  public static final char reduceIndexed(@NotNull CharSequence paramCharSequence, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> paramFunction3)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction3, "operation");
    int i = paramCharSequence.length();
    int j = 1;
    if (i == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      throw ((Throwable)new UnsupportedOperationException("Empty char sequence can't be reduced."));
    }
    char c2 = paramCharSequence.charAt(0);
    int k = StringsKt.getLastIndex(paramCharSequence);
    char c1 = c2;
    if (1 <= k)
    {
      i = j;
      for (;;)
      {
        c2 = ((Character)paramFunction3.invoke(Integer.valueOf(i), Character.valueOf(c2), Character.valueOf(paramCharSequence.charAt(i)))).charValue();
        c1 = c2;
        if (i == k) {
          break;
        }
        i += 1;
      }
    }
    return c1;
  }
  
  public static final char reduceRight(@NotNull CharSequence paramCharSequence, @NotNull Function2<? super Character, ? super Character, Character> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
    int j = StringsKt.getLastIndex(paramCharSequence);
    if (j < 0) {
      throw ((Throwable)new UnsupportedOperationException("Empty char sequence can't be reduced."));
    }
    int i = j - 1;
    char c = paramCharSequence.charAt(j);
    while (i >= 0)
    {
      c = ((Character)paramFunction2.invoke(Character.valueOf(paramCharSequence.charAt(i)), Character.valueOf(c))).charValue();
      i -= 1;
    }
    return c;
  }
  
  public static final char reduceRightIndexed(@NotNull CharSequence paramCharSequence, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> paramFunction3)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction3, "operation");
    int j = StringsKt.getLastIndex(paramCharSequence);
    if (j < 0) {
      throw ((Throwable)new UnsupportedOperationException("Empty char sequence can't be reduced."));
    }
    int i = j - 1;
    char c = paramCharSequence.charAt(j);
    while (i >= 0)
    {
      c = ((Character)paramFunction3.invoke(Integer.valueOf(i), Character.valueOf(paramCharSequence.charAt(i)), Character.valueOf(c))).charValue();
      i -= 1;
    }
    return c;
  }
  
  @NotNull
  public static final CharSequence reversed(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    paramCharSequence = new StringBuilder(paramCharSequence).reverse();
    Intrinsics.checkExpressionValueIsNotNull(paramCharSequence, "StringBuilder(this).reverse()");
    return (CharSequence)paramCharSequence;
  }
  
  @InlineOnly
  private static final String reversed(@NotNull String paramString)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
    return StringsKt.reversed((CharSequence)paramString).toString();
  }
  
  public static final char single(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    switch (paramCharSequence.length())
    {
    default: 
      throw ((Throwable)new IllegalArgumentException("Char sequence has more than one element."));
    case 1: 
      return paramCharSequence.charAt(0);
    }
    throw ((Throwable)new NoSuchElementException("Char sequence is empty."));
  }
  
  public static final char single(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    Character localCharacter = (Character)null;
    int i = 0;
    int k;
    for (int j = 0; i < paramCharSequence.length(); j = k)
    {
      char c = paramCharSequence.charAt(i);
      k = j;
      if (((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue())
      {
        if (j != 0) {
          throw ((Throwable)new IllegalArgumentException("Char sequence contains more than one matching element."));
        }
        localCharacter = Character.valueOf(c);
        k = 1;
      }
      i += 1;
    }
    if (j == 0) {
      throw ((Throwable)new NoSuchElementException("Char sequence contains no character matching the predicate."));
    }
    if (localCharacter == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.Char");
    }
    return localCharacter.charValue();
  }
  
  @Nullable
  public static final Character singleOrNull(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    if (paramCharSequence.length() == 1) {
      return Character.valueOf(paramCharSequence.charAt(0));
    }
    return null;
  }
  
  @Nullable
  public static final Character singleOrNull(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    Character localCharacter = (Character)null;
    int i = 0;
    int k;
    for (int j = 0; i < paramCharSequence.length(); j = k)
    {
      char c = paramCharSequence.charAt(i);
      k = j;
      if (((Boolean)paramFunction1.invoke(Character.valueOf(c))).booleanValue())
      {
        if (j != 0) {
          return null;
        }
        localCharacter = Character.valueOf(c);
        k = 1;
      }
      i += 1;
    }
    if (j == 0) {
      return null;
    }
    return localCharacter;
  }
  
  @NotNull
  public static final CharSequence slice(@NotNull CharSequence paramCharSequence, @NotNull Iterable<Integer> paramIterable)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIterable, "indices");
    int i = CollectionsKt.collectionSizeOrDefault(paramIterable, 10);
    if (i == 0) {
      return (CharSequence)"";
    }
    StringBuilder localStringBuilder = new StringBuilder(i);
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      localStringBuilder.append(paramCharSequence.charAt(((Number)paramIterable.next()).intValue()));
    }
    return (CharSequence)localStringBuilder;
  }
  
  @NotNull
  public static final CharSequence slice(@NotNull CharSequence paramCharSequence, @NotNull IntRange paramIntRange)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIntRange, "indices");
    if (paramIntRange.isEmpty()) {
      return (CharSequence)"";
    }
    return StringsKt.subSequence(paramCharSequence, paramIntRange);
  }
  
  @InlineOnly
  private static final String slice(@NotNull String paramString, Iterable<Integer> paramIterable)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
    return StringsKt.slice((CharSequence)paramString, paramIterable).toString();
  }
  
  @NotNull
  public static final String slice(@NotNull String paramString, @NotNull IntRange paramIntRange)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIntRange, "indices");
    if (paramIntRange.isEmpty()) {
      return "";
    }
    return StringsKt.substring(paramString, paramIntRange);
  }
  
  public static final int sumBy(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Integer> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "selector");
    int i = 0;
    int j = 0;
    while (i < paramCharSequence.length())
    {
      j += ((Number)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)))).intValue();
      i += 1;
    }
    return j;
  }
  
  public static final double sumByDouble(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Double> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "selector");
    double d = 0.0D;
    int i = 0;
    while (i < paramCharSequence.length())
    {
      d += ((Number)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)))).doubleValue();
      i += 1;
    }
    return d;
  }
  
  @NotNull
  public static final CharSequence take(@NotNull CharSequence paramCharSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int i;
    if (paramInt >= 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      paramCharSequence = new StringBuilder();
      paramCharSequence.append("Requested character count ");
      paramCharSequence.append(paramInt);
      paramCharSequence.append(" is less than zero.");
      throw ((Throwable)new IllegalArgumentException(paramCharSequence.toString().toString()));
    }
    return paramCharSequence.subSequence(0, RangesKt.coerceAtMost(paramInt, paramCharSequence.length()));
  }
  
  @NotNull
  public static final String take(@NotNull String paramString, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    int i;
    if (paramInt >= 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      paramString = new StringBuilder();
      paramString.append("Requested character count ");
      paramString.append(paramInt);
      paramString.append(" is less than zero.");
      throw ((Throwable)new IllegalArgumentException(paramString.toString().toString()));
    }
    paramString = paramString.substring(0, RangesKt.coerceAtMost(paramInt, paramString.length()));
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.Strin…ing(startIndex, endIndex)");
    return paramString;
  }
  
  @NotNull
  public static final CharSequence takeLast(@NotNull CharSequence paramCharSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    if (paramInt >= 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      paramCharSequence = new StringBuilder();
      paramCharSequence.append("Requested character count ");
      paramCharSequence.append(paramInt);
      paramCharSequence.append(" is less than zero.");
      throw ((Throwable)new IllegalArgumentException(paramCharSequence.toString().toString()));
    }
    int i = paramCharSequence.length();
    return paramCharSequence.subSequence(i - RangesKt.coerceAtMost(paramInt, i), i);
  }
  
  @NotNull
  public static final String takeLast(@NotNull String paramString, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    if (paramInt >= 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      paramString = new StringBuilder();
      paramString.append("Requested character count ");
      paramString.append(paramInt);
      paramString.append(" is less than zero.");
      throw ((Throwable)new IllegalArgumentException(paramString.toString().toString()));
    }
    int i = paramString.length();
    paramString = paramString.substring(i - RangesKt.coerceAtMost(paramInt, i));
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).substring(startIndex)");
    return paramString;
  }
  
  @NotNull
  public static final CharSequence takeLastWhile(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int i = StringsKt.getLastIndex(paramCharSequence);
    while (i >= 0)
    {
      if (!((Boolean)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)))).booleanValue()) {
        return paramCharSequence.subSequence(i + 1, paramCharSequence.length());
      }
      i -= 1;
    }
    return paramCharSequence.subSequence(0, paramCharSequence.length());
  }
  
  @NotNull
  public static final String takeLastWhile(@NotNull String paramString, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int i = StringsKt.getLastIndex((CharSequence)paramString);
    while (i >= 0)
    {
      if (!((Boolean)paramFunction1.invoke(Character.valueOf(paramString.charAt(i)))).booleanValue())
      {
        paramString = paramString.substring(i + 1);
        Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).substring(startIndex)");
        return paramString;
      }
      i -= 1;
    }
    return paramString;
  }
  
  @NotNull
  public static final CharSequence takeWhile(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int j = paramCharSequence.length();
    int i = 0;
    while (i < j)
    {
      if (!((Boolean)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)))).booleanValue()) {
        return paramCharSequence.subSequence(0, i);
      }
      i += 1;
    }
    return paramCharSequence.subSequence(0, paramCharSequence.length());
  }
  
  @NotNull
  public static final String takeWhile(@NotNull String paramString, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      if (!((Boolean)paramFunction1.invoke(Character.valueOf(paramString.charAt(i)))).booleanValue())
      {
        paramString = paramString.substring(0, i);
        Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return paramString;
      }
      i += 1;
    }
    return paramString;
  }
  
  @NotNull
  public static final <C extends Collection<? super Character>> C toCollection(@NotNull CharSequence paramCharSequence, @NotNull C paramC)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    int i = 0;
    while (i < paramCharSequence.length())
    {
      paramC.add(Character.valueOf(paramCharSequence.charAt(i)));
      i += 1;
    }
    return paramC;
  }
  
  @NotNull
  public static final HashSet<Character> toHashSet(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    return (HashSet)StringsKt.toCollection(paramCharSequence, (Collection)new HashSet(MapsKt.mapCapacity(paramCharSequence.length())));
  }
  
  @NotNull
  public static final List<Character> toList(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    switch (paramCharSequence.length())
    {
    default: 
      return StringsKt.toMutableList(paramCharSequence);
    case 1: 
      return CollectionsKt.listOf(Character.valueOf(paramCharSequence.charAt(0)));
    }
    return CollectionsKt.emptyList();
  }
  
  @NotNull
  public static final List<Character> toMutableList(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    return (List)StringsKt.toCollection(paramCharSequence, (Collection)new ArrayList(paramCharSequence.length()));
  }
  
  @NotNull
  public static final Set<Character> toSet(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    switch (paramCharSequence.length())
    {
    default: 
      return (Set)StringsKt.toCollection(paramCharSequence, (Collection)new LinkedHashSet(MapsKt.mapCapacity(paramCharSequence.length())));
    case 1: 
      return SetsKt.setOf(Character.valueOf(paramCharSequence.charAt(0)));
    }
    return SetsKt.emptySet();
  }
  
  @NotNull
  public static final SortedSet<Character> toSortedSet(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    return (SortedSet)StringsKt.toCollection(paramCharSequence, (Collection)new TreeSet());
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final List<String> windowed(@NotNull CharSequence paramCharSequence, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    return StringsKt.windowed(paramCharSequence, paramInt1, paramInt2, paramBoolean, (Function1)windowed.1.INSTANCE);
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final <R> List<R> windowed(@NotNull CharSequence paramCharSequence, int paramInt1, int paramInt2, boolean paramBoolean, @NotNull Function1<? super CharSequence, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    SlidingWindowKt.checkWindowSizeStep(paramInt1, paramInt2);
    int k = paramCharSequence.length();
    ArrayList localArrayList = new ArrayList((k + paramInt2 - 1) / paramInt2);
    int i = 0;
    while (i < k)
    {
      int m = i + paramInt1;
      int j = m;
      if (m > k)
      {
        if (!paramBoolean) {
          break;
        }
        j = k;
      }
      localArrayList.add(paramFunction1.invoke(paramCharSequence.subSequence(i, j)));
      i += paramInt2;
    }
    return (List)localArrayList;
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final Sequence<String> windowedSequence(@NotNull CharSequence paramCharSequence, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    return StringsKt.windowedSequence(paramCharSequence, paramInt1, paramInt2, paramBoolean, (Function1)windowedSequence.1.INSTANCE);
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final <R> Sequence<R> windowedSequence(@NotNull CharSequence paramCharSequence, final int paramInt1, int paramInt2, boolean paramBoolean, @NotNull final Function1<? super CharSequence, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    SlidingWindowKt.checkWindowSizeStep(paramInt1, paramInt2);
    IntRange localIntRange;
    if (paramBoolean) {
      localIntRange = StringsKt.getIndices(paramCharSequence);
    } else {
      localIntRange = RangesKt.until(0, paramCharSequence.length() - paramInt1 + 1);
    }
    SequencesKt.map(CollectionsKt.asSequence((Iterable)RangesKt.step((IntProgression)localIntRange, paramInt2)), (Function1)new Lambda(paramCharSequence)
    {
      public final R invoke(int paramAnonymousInt)
      {
        return paramFunction1.invoke(receiver$0.subSequence(paramAnonymousInt, RangesKt.coerceAtMost(paramInt1 + paramAnonymousInt, receiver$0.length())));
      }
    });
  }
  
  @NotNull
  public static final Iterable<IndexedValue<Character>> withIndex(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    (Iterable)new IndexingIterable((Function0)new Lambda(paramCharSequence)
    {
      @NotNull
      public final CharIterator invoke()
      {
        return StringsKt.iterator(receiver$0);
      }
    });
  }
  
  @NotNull
  public static final List<Pair<Character, Character>> zip(@NotNull CharSequence paramCharSequence1, @NotNull CharSequence paramCharSequence2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "other");
    int j = Math.min(paramCharSequence1.length(), paramCharSequence2.length());
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      localArrayList.add(TuplesKt.to(Character.valueOf(paramCharSequence1.charAt(i)), Character.valueOf(paramCharSequence2.charAt(i))));
      i += 1;
    }
    return (List)localArrayList;
  }
  
  @NotNull
  public static final <V> List<V> zip(@NotNull CharSequence paramCharSequence1, @NotNull CharSequence paramCharSequence2, @NotNull Function2<? super Character, ? super Character, ? extends V> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "other");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "transform");
    int j = Math.min(paramCharSequence1.length(), paramCharSequence2.length());
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      localArrayList.add(paramFunction2.invoke(Character.valueOf(paramCharSequence1.charAt(i)), Character.valueOf(paramCharSequence2.charAt(i))));
      i += 1;
    }
    return (List)localArrayList;
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final List<Pair<Character, Character>> zipWithNext(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int j = paramCharSequence.length() - 1;
    if (j < 1) {
      return CollectionsKt.emptyList();
    }
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      char c = paramCharSequence.charAt(i);
      i += 1;
      localArrayList.add(TuplesKt.to(Character.valueOf(c), Character.valueOf(paramCharSequence.charAt(i))));
    }
    return (List)localArrayList;
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final <R> List<R> zipWithNext(@NotNull CharSequence paramCharSequence, @NotNull Function2<? super Character, ? super Character, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "transform");
    int j = paramCharSequence.length() - 1;
    if (j < 1) {
      return CollectionsKt.emptyList();
    }
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      char c = paramCharSequence.charAt(i);
      i += 1;
      localArrayList.add(paramFunction2.invoke(Character.valueOf(c), Character.valueOf(paramCharSequence.charAt(i))));
    }
    return (List)localArrayList;
  }
}
