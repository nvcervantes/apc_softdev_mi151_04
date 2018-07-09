package kotlin.sequences;

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
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.Grouping;
import kotlin.collections.IndexedValue;
import kotlin.collections.SetsKt;
import kotlin.collections.SlidingWindowKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt.compareBy.2;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt.compareByDescending.1;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.SequenceBuilder;
import kotlin.coroutines.experimental.SequenceBuilderKt;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\002\n\000\n\002\020\013\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\034\n\002\b\002\n\002\020$\n\002\b\003\n\002\030\002\n\002\b\005\n\002\020%\n\002\b\005\n\002\020\006\n\002\020\005\n\002\b\002\n\002\020\007\n\000\n\002\020\b\n\000\n\002\020\t\n\000\n\002\020\n\n\002\b\002\n\002\020 \n\002\b\003\n\002\030\002\n\002\b\022\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\037\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\020\000\n\002\b\022\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\004\n\002\020!\n\000\n\002\030\002\n\002\b\006\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\r\n\002\b\006\n\002\020\016\n\002\b\f\n\002\020\017\n\002\b\006\n\002\030\002\n\002\030\002\n\002\b\007\n\002\020\021\n\002\b!\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020#\n\000\n\002\020\"\n\000\n\002\030\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\006\032-\020\000\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b\032\026\020\006\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\003\032-\020\006\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b\032\034\020\007\032\b\022\004\022\002H\0020\b\"\004\b\000\020\002*\b\022\004\022\002H\0020\003\032\037\020\t\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\003H\b\032Q\020\n\032\016\022\004\022\002H\f\022\004\022\002H\r0\013\"\004\b\000\020\002\"\004\b\001\020\f\"\004\b\002\020\r*\b\022\004\022\002H\0020\0032\036\020\016\032\032\022\004\022\002H\002\022\020\022\016\022\004\022\002H\f\022\004\022\002H\r0\0170\005H\b\032?\020\020\032\016\022\004\022\002H\f\022\004\022\002H\0020\013\"\004\b\000\020\002\"\004\b\001\020\f*\b\022\004\022\002H\0020\0032\022\020\021\032\016\022\004\022\002H\002\022\004\022\002H\f0\005H\b\032Y\020\020\032\016\022\004\022\002H\f\022\004\022\002H\r0\013\"\004\b\000\020\002\"\004\b\001\020\f\"\004\b\002\020\r*\b\022\004\022\002H\0020\0032\022\020\021\032\016\022\004\022\002H\002\022\004\022\002H\f0\0052\022\020\022\032\016\022\004\022\002H\002\022\004\022\002H\r0\005H\b\032Z\020\023\032\002H\024\"\004\b\000\020\002\"\004\b\001\020\f\"\030\b\002\020\024*\022\022\006\b\000\022\002H\f\022\006\b\000\022\002H\0020\025*\b\022\004\022\002H\0020\0032\006\020\026\032\002H\0242\022\020\021\032\016\022\004\022\002H\002\022\004\022\002H\f0\005H\b¢\006\002\020\027\032t\020\023\032\002H\024\"\004\b\000\020\002\"\004\b\001\020\f\"\004\b\002\020\r\"\030\b\003\020\024*\022\022\006\b\000\022\002H\f\022\006\b\000\022\002H\r0\025*\b\022\004\022\002H\0020\0032\006\020\026\032\002H\0242\022\020\021\032\016\022\004\022\002H\002\022\004\022\002H\f0\0052\022\020\022\032\016\022\004\022\002H\002\022\004\022\002H\r0\005H\b¢\006\002\020\030\032l\020\031\032\002H\024\"\004\b\000\020\002\"\004\b\001\020\f\"\004\b\002\020\r\"\030\b\003\020\024*\022\022\006\b\000\022\002H\f\022\006\b\000\022\002H\r0\025*\b\022\004\022\002H\0020\0032\006\020\026\032\002H\0242\036\020\016\032\032\022\004\022\002H\002\022\020\022\016\022\004\022\002H\f\022\004\022\002H\r0\0170\005H\b¢\006\002\020\027\032\027\020\032\032\0020\033*\b\022\004\022\0020\0340\003H\007¢\006\002\b\035\032\027\020\032\032\0020\033*\b\022\004\022\0020\0330\003H\007¢\006\002\b\036\032\027\020\032\032\0020\033*\b\022\004\022\0020\0370\003H\007¢\006\002\b \032\027\020\032\032\0020\033*\b\022\004\022\0020!0\003H\007¢\006\002\b\"\032\027\020\032\032\0020\033*\b\022\004\022\0020#0\003H\007¢\006\002\b$\032\027\020\032\032\0020\033*\b\022\004\022\0020%0\003H\007¢\006\002\b&\032,\020'\032\016\022\n\022\b\022\004\022\002H\0020(0\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020)\032\0020!H\007\032F\020'\032\b\022\004\022\002H*0\003\"\004\b\000\020\002\"\004\b\001\020**\b\022\004\022\002H\0020\0032\006\020)\032\0020!2\030\020\016\032\024\022\n\022\b\022\004\022\002H\0020(\022\004\022\002H*0\005H\007\032+\020+\032\0020\001\"\t\b\000\020\002¢\006\002\b,*\b\022\004\022\002H\0020\0032\006\020-\032\002H\002H\002¢\006\002\020.\032\026\020/\032\0020!\"\004\b\000\020\002*\b\022\004\022\002H\0020\003\032-\020/\032\0020!\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b\032\034\0200\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\003\0326\0201\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002\"\004\b\001\020\f*\b\022\004\022\002H\0020\0032\022\0202\032\016\022\004\022\002H\002\022\004\022\002H\f0\005\032$\0203\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\0204\032\0020!\0320\0205\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005\032#\0206\032\002H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\0207\032\0020!¢\006\002\0208\0327\0209\032\002H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\0207\032\0020!2\022\020:\032\016\022\004\022\0020!\022\004\022\002H\0020\005¢\006\002\020;\032%\020<\032\004\030\001H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\0207\032\0020!¢\006\002\0208\0320\020=\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005\032E\020>\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032'\020\004\032#\022\023\022\0210!¢\006\f\b@\022\b\bA\022\004\b\b(7\022\004\022\002H\002\022\004\022\0020\0010?\032a\020B\032\002HC\"\004\b\000\020\002\"\020\b\001\020C*\n\022\006\b\000\022\002H\0020D*\b\022\004\022\002H\0020\0032\006\020\026\032\002HC2'\020\004\032#\022\023\022\0210!¢\006\f\b@\022\b\bA\022\004\b\b(7\022\004\022\002H\002\022\004\022\0020\0010?H\b¢\006\002\020E\032$\020F\032\r\022\t\022\007H*¢\006\002\bG0\003\"\006\b\000\020*\030\001*\006\022\002\b\0030\003H\b\032(\020F\032\b\022\004\022\002H*0\003\"\004\b\000\020**\006\022\002\b\0030\0032\f\020H\032\b\022\004\022\002H*0I\0328\020J\032\002HC\"\006\b\000\020*\030\001\"\020\b\001\020C*\n\022\006\b\000\022\002H*0D*\006\022\002\b\0030\0032\006\020\026\032\002HCH\b¢\006\002\020K\032A\020J\032\002HC\"\020\b\000\020C*\n\022\006\b\000\022\002H*0D\"\004\b\001\020**\006\022\002\b\0030\0032\006\020\026\032\002HC2\f\020H\032\b\022\004\022\002H*0I¢\006\002\020L\0320\020M\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005\032\"\020N\032\b\022\004\022\002H\0020\003\"\b\b\000\020\002*\0020O*\n\022\006\022\004\030\001H\0020\003\032;\020P\032\002HC\"\020\b\000\020C*\n\022\006\b\000\022\002H\0020D\"\b\b\001\020\002*\0020O*\n\022\006\022\004\030\001H\0020\0032\006\020\026\032\002HC¢\006\002\020K\032L\020Q\032\002HC\"\004\b\000\020\002\"\020\b\001\020C*\n\022\006\b\000\022\002H\0020D*\b\022\004\022\002H\0020\0032\006\020\026\032\002HC2\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b¢\006\002\020R\032L\020S\032\002HC\"\004\b\000\020\002\"\020\b\001\020C*\n\022\006\b\000\022\002H\0020D*\b\022\004\022\002H\0020\0032\006\020\026\032\002HC2\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b¢\006\002\020R\0324\020T\032\004\030\001H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b¢\006\002\020U\0324\020V\032\004\030\001H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b¢\006\002\020U\032\033\020W\032\002H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\003¢\006\002\020X\0322\020W\032\002H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b¢\006\002\020U\032\035\020Y\032\004\030\001H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\003¢\006\002\020X\0324\020Y\032\004\030\001H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b¢\006\002\020U\032<\020Z\032\b\022\004\022\002H*0\003\"\004\b\000\020\002\"\004\b\001\020**\b\022\004\022\002H\0020\0032\030\020\016\032\024\022\004\022\002H\002\022\n\022\b\022\004\022\002H*0\0030\005\032X\020[\032\002HC\"\004\b\000\020\002\"\004\b\001\020*\"\020\b\002\020C*\n\022\006\b\000\022\002H*0D*\b\022\004\022\002H\0020\0032\006\020\026\032\002HC2\030\020\016\032\024\022\004\022\002H\002\022\n\022\b\022\004\022\002H*0\0030\005H\b¢\006\002\020R\032U\020\\\032\002H*\"\004\b\000\020\002\"\004\b\001\020**\b\022\004\022\002H\0020\0032\006\020]\032\002H*2'\020^\032#\022\023\022\021H*¢\006\f\b@\022\b\bA\022\004\b\b(_\022\004\022\002H\002\022\004\022\002H*0?H\b¢\006\002\020`\032j\020a\032\002H*\"\004\b\000\020\002\"\004\b\001\020**\b\022\004\022\002H\0020\0032\006\020]\032\002H*2<\020^\0328\022\023\022\0210!¢\006\f\b@\022\b\bA\022\004\b\b(7\022\023\022\021H*¢\006\f\b@\022\b\bA\022\004\b\b(_\022\004\022\002H\002\022\004\022\002H*0bH\b¢\006\002\020c\032-\020d\032\0020e\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020f\032\016\022\004\022\002H\002\022\004\022\0020e0\005H\b\032B\020g\032\0020e\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032'\020f\032#\022\023\022\0210!¢\006\f\b@\022\b\bA\022\004\b\b(7\022\004\022\002H\002\022\004\022\0020e0?H\b\032E\020h\032\024\022\004\022\002H\f\022\n\022\b\022\004\022\002H\0020(0\013\"\004\b\000\020\002\"\004\b\001\020\f*\b\022\004\022\002H\0020\0032\022\020\021\032\016\022\004\022\002H\002\022\004\022\002H\f0\005H\b\032_\020h\032\024\022\004\022\002H\f\022\n\022\b\022\004\022\002H\r0(0\013\"\004\b\000\020\002\"\004\b\001\020\f\"\004\b\002\020\r*\b\022\004\022\002H\0020\0032\022\020\021\032\016\022\004\022\002H\002\022\004\022\002H\f0\0052\022\020\022\032\016\022\004\022\002H\002\022\004\022\002H\r0\005H\b\032^\020i\032\002H\024\"\004\b\000\020\002\"\004\b\001\020\f\"\034\b\002\020\024*\026\022\006\b\000\022\002H\f\022\n\022\b\022\004\022\002H\0020j0\025*\b\022\004\022\002H\0020\0032\006\020\026\032\002H\0242\022\020\021\032\016\022\004\022\002H\002\022\004\022\002H\f0\005H\b¢\006\002\020\027\032x\020i\032\002H\024\"\004\b\000\020\002\"\004\b\001\020\f\"\004\b\002\020\r\"\034\b\003\020\024*\026\022\006\b\000\022\002H\f\022\n\022\b\022\004\022\002H\r0j0\025*\b\022\004\022\002H\0020\0032\006\020\026\032\002H\0242\022\020\021\032\016\022\004\022\002H\002\022\004\022\002H\f0\0052\022\020\022\032\016\022\004\022\002H\002\022\004\022\002H\r0\005H\b¢\006\002\020\030\032A\020k\032\016\022\004\022\002H\002\022\004\022\002H\f0l\"\004\b\000\020\002\"\004\b\001\020\f*\b\022\004\022\002H\0020\0032\024\b\004\020\021\032\016\022\004\022\002H\002\022\004\022\002H\f0\005H\b\032(\020m\032\0020!\"\t\b\000\020\002¢\006\002\b,*\b\022\004\022\002H\0020\0032\006\020-\032\002H\002¢\006\002\020n\032-\020o\032\0020!\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b\032-\020p\032\0020!\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b\032{\020q\032\002Hr\"\004\b\000\020\002\"\f\b\001\020r*\0060sj\002`t*\b\022\004\022\002H\0020\0032\006\020u\032\002Hr2\b\b\002\020v\032\0020w2\b\b\002\020x\032\0020w2\b\b\002\020y\032\0020w2\b\b\002\020z\032\0020!2\b\b\002\020{\032\0020w2\026\b\002\020\016\032\020\022\004\022\002H\002\022\004\022\0020w\030\0010\005¢\006\002\020|\032`\020}\032\0020~\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\b\b\002\020v\032\0020w2\b\b\002\020x\032\0020w2\b\b\002\020y\032\0020w2\b\b\002\020z\032\0020!2\b\b\002\020{\032\0020w2\026\b\002\020\016\032\020\022\004\022\002H\002\022\004\022\0020w\030\0010\005\032\033\020\032\002H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\003¢\006\002\020X\0322\020\032\002H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b¢\006\002\020U\032)\020\001\032\0020!\"\t\b\000\020\002¢\006\002\b,*\b\022\004\022\002H\0020\0032\006\020-\032\002H\002¢\006\002\020n\032\036\020\001\032\004\030\001H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\003¢\006\002\020X\0325\020\001\032\004\030\001H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b¢\006\002\020U\0327\020\001\032\b\022\004\022\002H*0\003\"\004\b\000\020\002\"\004\b\001\020**\b\022\004\022\002H\0020\0032\022\020\016\032\016\022\004\022\002H\002\022\004\022\002H*0\005\032L\020\001\032\b\022\004\022\002H*0\003\"\004\b\000\020\002\"\004\b\001\020**\b\022\004\022\002H\0020\0032'\020\016\032#\022\023\022\0210!¢\006\f\b@\022\b\bA\022\004\b\b(7\022\004\022\002H\002\022\004\022\002H*0?\032R\020\001\032\b\022\004\022\002H*0\003\"\004\b\000\020\002\"\b\b\001\020**\0020O*\b\022\004\022\002H\0020\0032)\020\016\032%\022\023\022\0210!¢\006\f\b@\022\b\bA\022\004\b\b(7\022\004\022\002H\002\022\006\022\004\030\001H*0?\032n\020\001\032\002HC\"\004\b\000\020\002\"\b\b\001\020**\0020O\"\020\b\002\020C*\n\022\006\b\000\022\002H*0D*\b\022\004\022\002H\0020\0032\006\020\026\032\002HC2)\020\016\032%\022\023\022\0210!¢\006\f\b@\022\b\bA\022\004\b\b(7\022\004\022\002H\002\022\006\022\004\030\001H*0?H\b¢\006\002\020E\032h\020\001\032\002HC\"\004\b\000\020\002\"\004\b\001\020*\"\020\b\002\020C*\n\022\006\b\000\022\002H*0D*\b\022\004\022\002H\0020\0032\006\020\026\032\002HC2'\020\016\032#\022\023\022\0210!¢\006\f\b@\022\b\bA\022\004\b\b(7\022\004\022\002H\002\022\004\022\002H*0?H\b¢\006\002\020E\032=\020\001\032\b\022\004\022\002H*0\003\"\004\b\000\020\002\"\b\b\001\020**\0020O*\b\022\004\022\002H\0020\0032\024\020\016\032\020\022\004\022\002H\002\022\006\022\004\030\001H*0\005\032Y\020\001\032\002HC\"\004\b\000\020\002\"\b\b\001\020**\0020O\"\020\b\002\020C*\n\022\006\b\000\022\002H*0D*\b\022\004\022\002H\0020\0032\006\020\026\032\002HC2\024\020\016\032\020\022\004\022\002H\002\022\006\022\004\030\001H*0\005H\b¢\006\002\020R\032S\020\001\032\002HC\"\004\b\000\020\002\"\004\b\001\020*\"\020\b\002\020C*\n\022\006\b\000\022\002H*0D*\b\022\004\022\002H\0020\0032\006\020\026\032\002HC2\022\020\016\032\016\022\004\022\002H\002\022\004\022\002H*0\005H\b¢\006\002\020R\032*\020\001\032\004\030\001H\002\"\017\b\000\020\002*\t\022\004\022\002H\0020\001*\b\022\004\022\002H\0020\003¢\006\003\020\001\032\033\020\001\032\004\030\0010\033*\b\022\004\022\0020\0330\003H\007¢\006\003\020\001\032\033\020\001\032\004\030\0010\037*\b\022\004\022\0020\0370\003H\007¢\006\003\020\001\032F\020\001\032\004\030\001H\002\"\004\b\000\020\002\"\017\b\001\020**\t\022\004\022\002H*0\001*\b\022\004\022\002H\0020\0032\022\0202\032\016\022\004\022\002H\002\022\004\022\002H*0\005H\b¢\006\002\020U\032>\020\001\032\004\030\001H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\035\020\001\032\030\022\006\b\000\022\002H\0020\001j\013\022\006\b\000\022\002H\002`\001¢\006\003\020\001\032*\020\001\032\004\030\001H\002\"\017\b\000\020\002*\t\022\004\022\002H\0020\001*\b\022\004\022\002H\0020\003¢\006\003\020\001\032\033\020\001\032\004\030\0010\033*\b\022\004\022\0020\0330\003H\007¢\006\003\020\001\032\033\020\001\032\004\030\0010\037*\b\022\004\022\0020\0370\003H\007¢\006\003\020\001\032F\020\001\032\004\030\001H\002\"\004\b\000\020\002\"\017\b\001\020**\t\022\004\022\002H*0\001*\b\022\004\022\002H\0020\0032\022\0202\032\016\022\004\022\002H\002\022\004\022\002H*0\005H\b¢\006\002\020U\032>\020\001\032\004\030\001H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\035\020\001\032\030\022\006\b\000\022\002H\0020\001j\013\022\006\b\000\022\002H\002`\001¢\006\003\020\001\032.\020\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020-\032\002H\002H\002¢\006\003\020\001\0328\020\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\020\020\001\032\013\022\006\b\001\022\002H\0020\001H\002¢\006\003\020\001\032/\020\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\r\020\001\032\b\022\004\022\002H\0020\bH\002\032/\020\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\r\020\001\032\b\022\004\022\002H\0020\003H\002\032.\020\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020-\032\002H\002H\b¢\006\003\020\001\032\027\020\001\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\003\032.\020\001\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b\0323\020\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020f\032\016\022\004\022\002H\002\022\004\022\0020e0\005H\007\032F\020 \001\032\032\022\n\022\b\022\004\022\002H\0020(\022\n\022\b\022\004\022\002H\0020(0\017\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b\032.\020¡\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020-\032\002H\002H\002¢\006\003\020\001\0328\020¡\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\020\020\001\032\013\022\006\b\001\022\002H\0020\001H\002¢\006\003\020\001\032/\020¡\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\r\020\001\032\b\022\004\022\002H\0020\bH\002\032/\020¡\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\r\020\001\032\b\022\004\022\002H\0020\003H\002\032.\020¢\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020-\032\002H\002H\b¢\006\003\020\001\032X\020£\001\032\003H¤\001\"\005\b\000\020¤\001\"\t\b\001\020\002*\003H¤\001*\b\022\004\022\002H\0020\0032)\020^\032%\022\024\022\022H¤\001¢\006\f\b@\022\b\bA\022\004\b\b(_\022\004\022\002H\002\022\005\022\003H¤\0010?H\b¢\006\003\020¥\001\032m\020¦\001\032\003H¤\001\"\005\b\000\020¤\001\"\t\b\001\020\002*\003H¤\001*\b\022\004\022\002H\0020\0032>\020^\032:\022\023\022\0210!¢\006\f\b@\022\b\bA\022\004\b\b(7\022\024\022\022H¤\001¢\006\f\b@\022\b\bA\022\004\b\b(_\022\004\022\002H\002\022\005\022\003H¤\0010bH\b¢\006\003\020§\001\032#\020¨\001\032\b\022\004\022\002H\0020\003\"\b\b\000\020\002*\0020O*\n\022\006\022\004\030\001H\0020\003\032\034\020©\001\032\002H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\003¢\006\002\020X\0323\020©\001\032\002H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b¢\006\002\020U\032\036\020ª\001\032\004\030\001H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\003¢\006\002\020X\0325\020ª\001\032\004\030\001H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b¢\006\002\020U\032(\020«\001\032\b\022\004\022\002H\0020\003\"\017\b\000\020\002*\t\022\004\022\002H\0020\001*\b\022\004\022\002H\0020\003\032I\020¬\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002\"\017\b\001\020**\t\022\004\022\002H*0\001*\b\022\004\022\002H\0020\0032\026\b\004\0202\032\020\022\004\022\002H\002\022\006\022\004\030\001H*0\005H\b\032I\020­\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002\"\017\b\001\020**\t\022\004\022\002H*0\001*\b\022\004\022\002H\0020\0032\026\b\004\0202\032\020\022\004\022\002H\002\022\006\022\004\030\001H*0\005H\b\032(\020®\001\032\b\022\004\022\002H\0020\003\"\017\b\000\020\002*\t\022\004\022\002H\0020\001*\b\022\004\022\002H\0020\003\032<\020¯\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\035\020\001\032\030\022\006\b\000\022\002H\0020\001j\013\022\006\b\000\022\002H\002`\001\032\031\020°\001\032\0020!*\b\022\004\022\0020\0340\003H\007¢\006\003\b±\001\032\031\020°\001\032\0020\033*\b\022\004\022\0020\0330\003H\007¢\006\003\b²\001\032\031\020°\001\032\0020\037*\b\022\004\022\0020\0370\003H\007¢\006\003\b³\001\032\031\020°\001\032\0020!*\b\022\004\022\0020!0\003H\007¢\006\003\b´\001\032\031\020°\001\032\0020#*\b\022\004\022\0020#0\003H\007¢\006\003\bµ\001\032\031\020°\001\032\0020!*\b\022\004\022\0020%0\003H\007¢\006\003\b¶\001\032.\020·\001\032\0020!\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\0202\032\016\022\004\022\002H\002\022\004\022\0020!0\005H\b\032.\020¸\001\032\0020\033\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\0202\032\016\022\004\022\002H\002\022\004\022\0020\0330\005H\b\032%\020¹\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\0204\032\0020!\0321\020º\001\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005\0326\020»\001\032\002HC\"\004\b\000\020\002\"\020\b\001\020C*\n\022\006\b\000\022\002H\0020D*\b\022\004\022\002H\0020\0032\006\020\026\032\002HC¢\006\002\020K\032)\020¼\001\032\024\022\004\022\002H\0020½\001j\t\022\004\022\002H\002`¾\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\003\032\035\020¿\001\032\b\022\004\022\002H\0020(\"\004\b\000\020\002*\b\022\004\022\002H\0020\003\032\035\020À\001\032\b\022\004\022\002H\0020j\"\004\b\000\020\002*\b\022\004\022\002H\0020\003\032\036\020Á\001\032\t\022\004\022\002H\0020Â\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\003\032\036\020Ã\001\032\t\022\004\022\002H\0020Ä\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\003\0324\020Å\001\032\024\022\004\022\002H\0020Æ\001j\t\022\004\022\002H\002`Ç\001\"\017\b\000\020\002*\t\022\004\022\002H\0020\001*\b\022\004\022\002H\0020\003\032H\020Å\001\032\024\022\004\022\002H\0020Æ\001j\t\022\004\022\002H\002`Ç\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\035\020\001\032\030\022\006\b\000\022\002H\0020\001j\013\022\006\b\000\022\002H\002`\001\032C\020È\001\032\016\022\n\022\b\022\004\022\002H\0020(0\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020)\032\0020!2\t\b\002\020É\001\032\0020!2\t\b\002\020Ê\001\032\0020\001H\007\032]\020È\001\032\b\022\004\022\002H*0\003\"\004\b\000\020\002\"\004\b\001\020**\b\022\004\022\002H\0020\0032\006\020)\032\0020!2\t\b\002\020É\001\032\0020!2\t\b\002\020Ê\001\032\0020\0012\030\020\016\032\024\022\n\022\b\022\004\022\002H\0020(\022\004\022\002H*0\005H\007\032$\020Ë\001\032\017\022\013\022\t\022\004\022\002H\0020Ì\0010\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\003\032A\020Í\001\032\024\022\020\022\016\022\004\022\002H\002\022\004\022\002H*0\0170\003\"\004\b\000\020\002\"\004\b\001\020**\b\022\004\022\002H\0020\0032\r\020Î\001\032\b\022\004\022\002H*0\003H\004\032r\020Í\001\032\b\022\004\022\002H\r0\003\"\004\b\000\020\002\"\004\b\001\020*\"\004\b\002\020\r*\b\022\004\022\002H\0020\0032\r\020Î\001\032\b\022\004\022\002H*0\00328\020\016\0324\022\024\022\022H\002¢\006\r\b@\022\t\bA\022\005\b\b(Ï\001\022\024\022\022H*¢\006\r\b@\022\t\bA\022\005\b\b(Ð\001\022\004\022\002H\r0?\032+\020Ñ\001\032\024\022\020\022\016\022\004\022\002H\002\022\004\022\002H\0020\0170\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\003H\007\032_\020Ñ\001\032\b\022\004\022\002H*0\003\"\004\b\000\020\002\"\004\b\001\020**\b\022\004\022\002H\0020\00328\020\016\0324\022\024\022\022H\002¢\006\r\b@\022\t\bA\022\005\b\b(Ï\001\022\024\022\022H\002¢\006\r\b@\022\t\bA\022\005\b\b(Ð\001\022\004\022\002H*0?H\007¨\006Ò\001"}, d2={"all", "", "T", "Lkotlin/sequences/Sequence;", "predicate", "Lkotlin/Function1;", "any", "asIterable", "", "asSequence", "associate", "", "K", "V", "transform", "Lkotlin/Pair;", "associateBy", "keySelector", "valueTransform", "associateByTo", "M", "", "destination", "(Lkotlin/sequences/Sequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "(Lkotlin/sequences/Sequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "associateTo", "average", "", "", "averageOfByte", "averageOfDouble", "", "averageOfFloat", "", "averageOfInt", "", "averageOfLong", "", "averageOfShort", "chunked", "", "size", "R", "contains", "Lkotlin/internal/OnlyInputTypes;", "element", "(Lkotlin/sequences/Sequence;Ljava/lang/Object;)Z", "count", "distinct", "distinctBy", "selector", "drop", "n", "dropWhile", "elementAt", "index", "(Lkotlin/sequences/Sequence;I)Ljava/lang/Object;", "elementAtOrElse", "defaultValue", "(Lkotlin/sequences/Sequence;ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "elementAtOrNull", "filter", "filterIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "filterIndexedTo", "C", "", "(Lkotlin/sequences/Sequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function2;)Ljava/util/Collection;", "filterIsInstance", "Lkotlin/internal/NoInfer;", "klass", "Ljava/lang/Class;", "filterIsInstanceTo", "(Lkotlin/sequences/Sequence;Ljava/util/Collection;)Ljava/util/Collection;", "(Lkotlin/sequences/Sequence;Ljava/util/Collection;Ljava/lang/Class;)Ljava/util/Collection;", "filterNot", "filterNotNull", "", "filterNotNullTo", "filterNotTo", "(Lkotlin/sequences/Sequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "filterTo", "find", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "findLast", "first", "(Lkotlin/sequences/Sequence;)Ljava/lang/Object;", "firstOrNull", "flatMap", "flatMapTo", "fold", "initial", "operation", "acc", "(Lkotlin/sequences/Sequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "foldIndexed", "Lkotlin/Function3;", "(Lkotlin/sequences/Sequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "forEach", "", "action", "forEachIndexed", "groupBy", "groupByTo", "", "groupingBy", "Lkotlin/collections/Grouping;", "indexOf", "(Lkotlin/sequences/Sequence;Ljava/lang/Object;)I", "indexOfFirst", "indexOfLast", "joinTo", "A", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "buffer", "separator", "", "prefix", "postfix", "limit", "truncated", "(Lkotlin/sequences/Sequence;Ljava/lang/Appendable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Appendable;", "joinToString", "", "last", "lastIndexOf", "lastOrNull", "map", "mapIndexed", "mapIndexedNotNull", "mapIndexedNotNullTo", "mapIndexedTo", "mapNotNull", "mapNotNullTo", "mapTo", "max", "", "(Lkotlin/sequences/Sequence;)Ljava/lang/Comparable;", "(Lkotlin/sequences/Sequence;)Ljava/lang/Double;", "(Lkotlin/sequences/Sequence;)Ljava/lang/Float;", "maxBy", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Lkotlin/sequences/Sequence;Ljava/util/Comparator;)Ljava/lang/Object;", "min", "minBy", "minWith", "minus", "(Lkotlin/sequences/Sequence;Ljava/lang/Object;)Lkotlin/sequences/Sequence;", "elements", "", "(Lkotlin/sequences/Sequence;[Ljava/lang/Object;)Lkotlin/sequences/Sequence;", "minusElement", "none", "onEach", "partition", "plus", "plusElement", "reduce", "S", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "reduceIndexed", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "requireNoNulls", "single", "singleOrNull", "sorted", "sortedBy", "sortedByDescending", "sortedDescending", "sortedWith", "sum", "sumOfByte", "sumOfDouble", "sumOfFloat", "sumOfInt", "sumOfLong", "sumOfShort", "sumBy", "sumByDouble", "take", "takeWhile", "toCollection", "toHashSet", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "toList", "toMutableList", "toMutableSet", "", "toSet", "", "toSortedSet", "Ljava/util/SortedSet;", "Lkotlin/collections/SortedSet;", "windowed", "step", "partialWindows", "withIndex", "Lkotlin/collections/IndexedValue;", "zip", "other", "a", "b", "zipWithNext", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/sequences/SequencesKt")
class SequencesKt___SequencesKt
  extends SequencesKt__SequencesKt
{
  public SequencesKt___SequencesKt() {}
  
  public static final <T> boolean all(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext()) {
      if (!((Boolean)paramFunction1.invoke(paramSequence.next())).booleanValue()) {
        return false;
      }
    }
    return true;
  }
  
  public static final <T> boolean any(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return paramSequence.iterator().hasNext();
  }
  
  public static final <T> boolean any(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext()) {
      if (((Boolean)paramFunction1.invoke(paramSequence.next())).booleanValue()) {
        return true;
      }
    }
    return false;
  }
  
  @NotNull
  public static final <T> Iterable<T> asIterable(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    (Iterable)new Iterable()
    {
      @NotNull
      public Iterator<T> iterator()
      {
        return receiver$0$inlined.iterator();
      }
    };
  }
  
  @InlineOnly
  private static final <T> Sequence<T> asSequence(@NotNull Sequence<? extends T> paramSequence)
  {
    return paramSequence;
  }
  
  @NotNull
  public static final <T, K, V> Map<K, V> associate(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, ? extends Pair<? extends K, ? extends V>> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    Map localMap = (Map)new LinkedHashMap();
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Pair localPair = (Pair)paramFunction1.invoke(paramSequence.next());
      localMap.put(localPair.getFirst(), localPair.getSecond());
    }
    return localMap;
  }
  
  @NotNull
  public static final <T, K> Map<K, T> associateBy(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, ? extends K> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    Map localMap = (Map)new LinkedHashMap();
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      localMap.put(paramFunction1.invoke(localObject), localObject);
    }
    return localMap;
  }
  
  @NotNull
  public static final <T, K, V> Map<K, V> associateBy(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, ? extends K> paramFunction1, @NotNull Function1<? super T, ? extends V> paramFunction11)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    Intrinsics.checkParameterIsNotNull(paramFunction11, "valueTransform");
    Map localMap = (Map)new LinkedHashMap();
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      localMap.put(paramFunction1.invoke(localObject), paramFunction11.invoke(localObject));
    }
    return localMap;
  }
  
  @NotNull
  public static final <T, K, M extends Map<? super K, ? super T>> M associateByTo(@NotNull Sequence<? extends T> paramSequence, @NotNull M paramM, @NotNull Function1<? super T, ? extends K> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      paramM.put(paramFunction1.invoke(localObject), localObject);
    }
    return paramM;
  }
  
  @NotNull
  public static final <T, K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull Sequence<? extends T> paramSequence, @NotNull M paramM, @NotNull Function1<? super T, ? extends K> paramFunction1, @NotNull Function1<? super T, ? extends V> paramFunction11)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    Intrinsics.checkParameterIsNotNull(paramFunction11, "valueTransform");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      paramM.put(paramFunction1.invoke(localObject), paramFunction11.invoke(localObject));
    }
    return paramM;
  }
  
  @NotNull
  public static final <T, K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull Sequence<? extends T> paramSequence, @NotNull M paramM, @NotNull Function1<? super T, ? extends Pair<? extends K, ? extends V>> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Pair localPair = (Pair)paramFunction1.invoke(paramSequence.next());
      paramM.put(localPair.getFirst(), localPair.getSecond());
    }
    return paramM;
  }
  
  @JvmName(name="averageOfByte")
  public static final double averageOfByte(@NotNull Sequence<Byte> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    double d = 0.0D;
    int i = 0;
    while (paramSequence.hasNext())
    {
      d += ((Number)paramSequence.next()).byteValue();
      i += 1;
    }
    if (i == 0) {
      return DoubleCompanionObject.INSTANCE.getNaN();
    }
    return d / i;
  }
  
  @JvmName(name="averageOfDouble")
  public static final double averageOfDouble(@NotNull Sequence<Double> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    double d = 0.0D;
    int i = 0;
    while (paramSequence.hasNext())
    {
      d += ((Number)paramSequence.next()).doubleValue();
      i += 1;
    }
    if (i == 0) {
      return DoubleCompanionObject.INSTANCE.getNaN();
    }
    return d / i;
  }
  
  @JvmName(name="averageOfFloat")
  public static final double averageOfFloat(@NotNull Sequence<Float> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    double d = 0.0D;
    int i = 0;
    while (paramSequence.hasNext())
    {
      d += ((Number)paramSequence.next()).floatValue();
      i += 1;
    }
    if (i == 0) {
      return DoubleCompanionObject.INSTANCE.getNaN();
    }
    return d / i;
  }
  
  @JvmName(name="averageOfInt")
  public static final double averageOfInt(@NotNull Sequence<Integer> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    double d = 0.0D;
    int i = 0;
    while (paramSequence.hasNext())
    {
      d += ((Number)paramSequence.next()).intValue();
      i += 1;
    }
    if (i == 0) {
      return DoubleCompanionObject.INSTANCE.getNaN();
    }
    return d / i;
  }
  
  @JvmName(name="averageOfLong")
  public static final double averageOfLong(@NotNull Sequence<Long> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    double d = 0.0D;
    int i = 0;
    while (paramSequence.hasNext())
    {
      d += ((Number)paramSequence.next()).longValue();
      i += 1;
    }
    if (i == 0) {
      return DoubleCompanionObject.INSTANCE.getNaN();
    }
    return d / i;
  }
  
  @JvmName(name="averageOfShort")
  public static final double averageOfShort(@NotNull Sequence<Short> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    double d = 0.0D;
    int i = 0;
    while (paramSequence.hasNext())
    {
      d += ((Number)paramSequence.next()).shortValue();
      i += 1;
    }
    if (i == 0) {
      return DoubleCompanionObject.INSTANCE.getNaN();
    }
    return d / i;
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final <T> Sequence<List<T>> chunked(@NotNull Sequence<? extends T> paramSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return SequencesKt.windowed(paramSequence, paramInt, paramInt, true);
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final <T, R> Sequence<R> chunked(@NotNull Sequence<? extends T> paramSequence, int paramInt, @NotNull Function1<? super List<? extends T>, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    return SequencesKt.windowed(paramSequence, paramInt, paramInt, true, paramFunction1);
  }
  
  public static final <T> boolean contains(@NotNull Sequence<? extends T> paramSequence, T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return SequencesKt.indexOf(paramSequence, paramT) >= 0;
  }
  
  public static final <T> int count(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    int i = 0;
    while (paramSequence.hasNext())
    {
      paramSequence.next();
      i += 1;
    }
    return i;
  }
  
  public static final <T> int count(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramSequence = paramSequence.iterator();
    int i = 0;
    while (paramSequence.hasNext()) {
      if (((Boolean)paramFunction1.invoke(paramSequence.next())).booleanValue()) {
        i += 1;
      }
    }
    return i;
  }
  
  @NotNull
  public static final <T> Sequence<T> distinct(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return SequencesKt.distinctBy(paramSequence, (Function1)distinct.1.INSTANCE);
  }
  
  @NotNull
  public static final <T, K> Sequence<T> distinctBy(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, ? extends K> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "selector");
    return (Sequence)new DistinctSequence(paramSequence, paramFunction1);
  }
  
  @NotNull
  public static final <T> Sequence<T> drop(@NotNull Sequence<? extends T> paramSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    int i;
    if (paramInt >= 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      paramSequence = new StringBuilder();
      paramSequence.append("Requested element count ");
      paramSequence.append(paramInt);
      paramSequence.append(" is less than zero.");
      throw ((Throwable)new IllegalArgumentException(paramSequence.toString().toString()));
    }
    if (paramInt == 0) {
      return paramSequence;
    }
    if ((paramSequence instanceof DropTakeSequence)) {
      return ((DropTakeSequence)paramSequence).drop(paramInt);
    }
    return (Sequence)new DropSequence(paramSequence, paramInt);
  }
  
  @NotNull
  public static final <T> Sequence<T> dropWhile(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    return (Sequence)new DropWhileSequence(paramSequence, paramFunction1);
  }
  
  public static final <T> T elementAt(@NotNull Sequence<? extends T> paramSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    SequencesKt.elementAtOrElse(paramSequence, paramInt, (Function1)new Lambda(paramInt)
    {
      @NotNull
      public final Void invoke(int paramAnonymousInt)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Sequence doesn't contain element at index ");
        localStringBuilder.append($index);
        localStringBuilder.append('.');
        throw ((Throwable)new IndexOutOfBoundsException(localStringBuilder.toString()));
      }
    });
  }
  
  public static final <T> T elementAtOrElse(@NotNull Sequence<? extends T> paramSequence, int paramInt, @NotNull Function1<? super Integer, ? extends T> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "defaultValue");
    if (paramInt < 0) {
      return paramFunction1.invoke(Integer.valueOf(paramInt));
    }
    paramSequence = paramSequence.iterator();
    int i = 0;
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      if (paramInt == i) {
        return localObject;
      }
      i += 1;
    }
    return paramFunction1.invoke(Integer.valueOf(paramInt));
  }
  
  @Nullable
  public static final <T> T elementAtOrNull(@NotNull Sequence<? extends T> paramSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    if (paramInt < 0) {
      return null;
    }
    paramSequence = paramSequence.iterator();
    int i = 0;
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      if (paramInt == i) {
        return localObject;
      }
      i += 1;
    }
    return null;
  }
  
  @NotNull
  public static final <T> Sequence<T> filter(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    return (Sequence)new FilteringSequence(paramSequence, true, paramFunction1);
  }
  
  @NotNull
  public static final <T> Sequence<T> filterIndexed(@NotNull Sequence<? extends T> paramSequence, @NotNull Function2<? super Integer, ? super T, Boolean> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "predicate");
    (Sequence)new TransformingSequence((Sequence)new FilteringSequence((Sequence)new IndexingSequence(paramSequence), true, (Function1)new Lambda(paramFunction2)
    {
      public final boolean invoke(@NotNull IndexedValue<? extends T> paramAnonymousIndexedValue)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousIndexedValue, "it");
        return ((Boolean)$predicate.invoke(Integer.valueOf(paramAnonymousIndexedValue.getIndex()), paramAnonymousIndexedValue.getValue())).booleanValue();
      }
    }), (Function1)filterIndexed.2.INSTANCE);
  }
  
  @NotNull
  public static final <T, C extends Collection<? super T>> C filterIndexedTo(@NotNull Sequence<? extends T> paramSequence, @NotNull C paramC, @NotNull Function2<? super Integer, ? super T, Boolean> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "predicate");
    paramSequence = paramSequence.iterator();
    int i = 0;
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      if (((Boolean)paramFunction2.invoke(Integer.valueOf(i), localObject)).booleanValue()) {
        paramC.add(localObject);
      }
      i += 1;
    }
    return paramC;
  }
  
  private static final <R> Sequence<R> filterIsInstance(@NotNull Sequence<?> paramSequence)
  {
    Intrinsics.needClassReification();
    paramSequence = SequencesKt.filter(paramSequence, (Function1)filterIsInstance.1.INSTANCE);
    if (paramSequence == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.sequences.Sequence<R>");
    }
    return paramSequence;
  }
  
  @NotNull
  public static final <R> Sequence<R> filterIsInstance(@NotNull Sequence<?> paramSequence, @NotNull Class<R> paramClass)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramClass, "klass");
    paramSequence = SequencesKt.filter(paramSequence, (Function1)new Lambda(paramClass)
    {
      public final boolean invoke(@Nullable Object paramAnonymousObject)
      {
        return $klass.isInstance(paramAnonymousObject);
      }
    });
    if (paramSequence == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.sequences.Sequence<R>");
    }
    return paramSequence;
  }
  
  private static final <R, C extends Collection<? super R>> C filterIsInstanceTo(@NotNull Sequence<?> paramSequence, C paramC)
  {
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      Intrinsics.reifiedOperationMarker(3, "R");
      if ((localObject instanceof Object)) {
        paramC.add(localObject);
      }
    }
    return paramC;
  }
  
  @NotNull
  public static final <C extends Collection<? super R>, R> C filterIsInstanceTo(@NotNull Sequence<?> paramSequence, @NotNull C paramC, @NotNull Class<R> paramClass)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramClass, "klass");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      if (paramClass.isInstance(localObject)) {
        paramC.add(localObject);
      }
    }
    return paramC;
  }
  
  @NotNull
  public static final <T> Sequence<T> filterNot(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    return (Sequence)new FilteringSequence(paramSequence, false, paramFunction1);
  }
  
  @NotNull
  public static final <T> Sequence<T> filterNotNull(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = SequencesKt.filterNot(paramSequence, (Function1)filterNotNull.1.INSTANCE);
    if (paramSequence == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.sequences.Sequence<T>");
    }
    return paramSequence;
  }
  
  @NotNull
  public static final <C extends Collection<? super T>, T> C filterNotNullTo(@NotNull Sequence<? extends T> paramSequence, @NotNull C paramC)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      if (localObject != null) {
        paramC.add(localObject);
      }
    }
    return paramC;
  }
  
  @NotNull
  public static final <T, C extends Collection<? super T>> C filterNotTo(@NotNull Sequence<? extends T> paramSequence, @NotNull C paramC, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      if (!((Boolean)paramFunction1.invoke(localObject)).booleanValue()) {
        paramC.add(localObject);
      }
    }
    return paramC;
  }
  
  @NotNull
  public static final <T, C extends Collection<? super T>> C filterTo(@NotNull Sequence<? extends T> paramSequence, @NotNull C paramC, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      if (((Boolean)paramFunction1.invoke(localObject)).booleanValue()) {
        paramC.add(localObject);
      }
    }
    return paramC;
  }
  
  @InlineOnly
  private static final <T> T find(@NotNull Sequence<? extends T> paramSequence, Function1<? super T, Boolean> paramFunction1)
  {
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      if (((Boolean)paramFunction1.invoke(localObject)).booleanValue()) {
        return localObject;
      }
    }
    return null;
  }
  
  @InlineOnly
  private static final <T> T findLast(@NotNull Sequence<? extends T> paramSequence, Function1<? super T, Boolean> paramFunction1)
  {
    Iterator localIterator = paramSequence.iterator();
    paramSequence = null;
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (((Boolean)paramFunction1.invoke(localObject)).booleanValue()) {
        paramSequence = localObject;
      }
    }
    return paramSequence;
  }
  
  public static final <T> T first(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    if (!paramSequence.hasNext()) {
      throw ((Throwable)new NoSuchElementException("Sequence is empty."));
    }
    return paramSequence.next();
  }
  
  public static final <T> T first(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      if (((Boolean)paramFunction1.invoke(localObject)).booleanValue()) {
        return localObject;
      }
    }
    throw ((Throwable)new NoSuchElementException("Sequence contains no element matching the predicate."));
  }
  
  @Nullable
  public static final <T> T firstOrNull(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    if (!paramSequence.hasNext()) {
      return null;
    }
    return paramSequence.next();
  }
  
  @Nullable
  public static final <T> T firstOrNull(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      if (((Boolean)paramFunction1.invoke(localObject)).booleanValue()) {
        return localObject;
      }
    }
    return null;
  }
  
  @NotNull
  public static final <T, R> Sequence<R> flatMap(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, ? extends Sequence<? extends R>> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    return (Sequence)new FlatteningSequence(paramSequence, paramFunction1, (Function1)flatMap.1.INSTANCE);
  }
  
  @NotNull
  public static final <T, R, C extends Collection<? super R>> C flatMapTo(@NotNull Sequence<? extends T> paramSequence, @NotNull C paramC, @NotNull Function1<? super T, ? extends Sequence<? extends R>> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext()) {
      CollectionsKt.addAll(paramC, (Sequence)paramFunction1.invoke(paramSequence.next()));
    }
    return paramC;
  }
  
  public static final <T, R> R fold(@NotNull Sequence<? extends T> paramSequence, R paramR, @NotNull Function2<? super R, ? super T, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext()) {
      paramR = paramFunction2.invoke(paramR, paramSequence.next());
    }
    return paramR;
  }
  
  public static final <T, R> R foldIndexed(@NotNull Sequence<? extends T> paramSequence, R paramR, @NotNull Function3<? super Integer, ? super R, ? super T, ? extends R> paramFunction3)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction3, "operation");
    paramSequence = paramSequence.iterator();
    int j;
    for (int i = 0; paramSequence.hasNext(); i = j)
    {
      Object localObject = paramSequence.next();
      j = i + 1;
      paramR = paramFunction3.invoke(Integer.valueOf(i), paramR, localObject);
    }
    return paramR;
  }
  
  public static final <T> void forEach(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Unit> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "action");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext()) {
      paramFunction1.invoke(paramSequence.next());
    }
  }
  
  public static final <T> void forEachIndexed(@NotNull Sequence<? extends T> paramSequence, @NotNull Function2<? super Integer, ? super T, Unit> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "action");
    paramSequence = paramSequence.iterator();
    int j;
    for (int i = 0; paramSequence.hasNext(); i = j)
    {
      Object localObject = paramSequence.next();
      j = i + 1;
      paramFunction2.invoke(Integer.valueOf(i), localObject);
    }
  }
  
  @NotNull
  public static final <T, K> Map<K, List<T>> groupBy(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, ? extends K> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    Map localMap = (Map)new LinkedHashMap();
    Iterator localIterator = paramSequence.iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = localIterator.next();
      Object localObject3 = paramFunction1.invoke(localObject2);
      Object localObject1 = localMap.get(localObject3);
      paramSequence = localObject1;
      if (localObject1 == null)
      {
        paramSequence = new ArrayList();
        localMap.put(localObject3, paramSequence);
      }
      ((List)paramSequence).add(localObject2);
    }
    return localMap;
  }
  
  @NotNull
  public static final <T, K, V> Map<K, List<V>> groupBy(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, ? extends K> paramFunction1, @NotNull Function1<? super T, ? extends V> paramFunction11)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    Intrinsics.checkParameterIsNotNull(paramFunction11, "valueTransform");
    Map localMap = (Map)new LinkedHashMap();
    Iterator localIterator = paramSequence.iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = localIterator.next();
      Object localObject3 = paramFunction1.invoke(localObject2);
      Object localObject1 = localMap.get(localObject3);
      paramSequence = localObject1;
      if (localObject1 == null)
      {
        paramSequence = new ArrayList();
        localMap.put(localObject3, paramSequence);
      }
      ((List)paramSequence).add(paramFunction11.invoke(localObject2));
    }
    return localMap;
  }
  
  @NotNull
  public static final <T, K, M extends Map<? super K, List<T>>> M groupByTo(@NotNull Sequence<? extends T> paramSequence, @NotNull M paramM, @NotNull Function1<? super T, ? extends K> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    Iterator localIterator = paramSequence.iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = localIterator.next();
      Object localObject3 = paramFunction1.invoke(localObject2);
      Object localObject1 = paramM.get(localObject3);
      paramSequence = localObject1;
      if (localObject1 == null)
      {
        paramSequence = new ArrayList();
        paramM.put(localObject3, paramSequence);
      }
      ((List)paramSequence).add(localObject2);
    }
    return paramM;
  }
  
  @NotNull
  public static final <T, K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull Sequence<? extends T> paramSequence, @NotNull M paramM, @NotNull Function1<? super T, ? extends K> paramFunction1, @NotNull Function1<? super T, ? extends V> paramFunction11)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    Intrinsics.checkParameterIsNotNull(paramFunction11, "valueTransform");
    Iterator localIterator = paramSequence.iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = localIterator.next();
      Object localObject3 = paramFunction1.invoke(localObject2);
      Object localObject1 = paramM.get(localObject3);
      paramSequence = localObject1;
      if (localObject1 == null)
      {
        paramSequence = new ArrayList();
        paramM.put(localObject3, paramSequence);
      }
      ((List)paramSequence).add(paramFunction11.invoke(localObject2));
    }
    return paramM;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T, K> Grouping<T, K> groupingBy(@NotNull Sequence<? extends T> paramSequence, @NotNull final Function1<? super T, ? extends K> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "keySelector");
    (Grouping)new Grouping()
    {
      public K keyOf(T paramAnonymousT)
      {
        return paramFunction1.invoke(paramAnonymousT);
      }
      
      @NotNull
      public Iterator<T> sourceIterator()
      {
        return receiver$0.iterator();
      }
    };
  }
  
  public static final <T> int indexOf(@NotNull Sequence<? extends T> paramSequence, T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    int i = 0;
    while (paramSequence.hasNext())
    {
      if (Intrinsics.areEqual(paramT, paramSequence.next())) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public static final <T> int indexOfFirst(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramSequence = paramSequence.iterator();
    int i = 0;
    while (paramSequence.hasNext())
    {
      if (((Boolean)paramFunction1.invoke(paramSequence.next())).booleanValue()) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public static final <T> int indexOfLast(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramSequence = paramSequence.iterator();
    int j = -1;
    int i = 0;
    while (paramSequence.hasNext())
    {
      if (((Boolean)paramFunction1.invoke(paramSequence.next())).booleanValue()) {
        j = i;
      }
      i += 1;
    }
    return j;
  }
  
  @NotNull
  public static final <T, A extends Appendable> A joinTo(@NotNull Sequence<? extends T> paramSequence, @NotNull A paramA, @NotNull CharSequence paramCharSequence1, @NotNull CharSequence paramCharSequence2, @NotNull CharSequence paramCharSequence3, int paramInt, @NotNull CharSequence paramCharSequence4, @Nullable Function1<? super T, ? extends CharSequence> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramA, "buffer");
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "separator");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "prefix");
    Intrinsics.checkParameterIsNotNull(paramCharSequence3, "postfix");
    Intrinsics.checkParameterIsNotNull(paramCharSequence4, "truncated");
    paramA.append(paramCharSequence2);
    paramSequence = paramSequence.iterator();
    int i = 0;
    int j;
    for (;;)
    {
      j = i;
      if (!paramSequence.hasNext()) {
        break;
      }
      paramCharSequence2 = paramSequence.next();
      i += 1;
      if (i > 1) {
        paramA.append(paramCharSequence1);
      }
      if (paramInt >= 0)
      {
        j = i;
        if (i > paramInt) {
          break;
        }
      }
      StringsKt.appendElement(paramA, paramCharSequence2, paramFunction1);
    }
    if ((paramInt >= 0) && (j > paramInt)) {
      paramA.append(paramCharSequence4);
    }
    paramA.append(paramCharSequence3);
    return paramA;
  }
  
  @NotNull
  public static final <T> String joinToString(@NotNull Sequence<? extends T> paramSequence, @NotNull CharSequence paramCharSequence1, @NotNull CharSequence paramCharSequence2, @NotNull CharSequence paramCharSequence3, int paramInt, @NotNull CharSequence paramCharSequence4, @Nullable Function1<? super T, ? extends CharSequence> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "separator");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "prefix");
    Intrinsics.checkParameterIsNotNull(paramCharSequence3, "postfix");
    Intrinsics.checkParameterIsNotNull(paramCharSequence4, "truncated");
    paramSequence = ((StringBuilder)SequencesKt.joinTo(paramSequence, (Appendable)new StringBuilder(), paramCharSequence1, paramCharSequence2, paramCharSequence3, paramInt, paramCharSequence4, paramFunction1)).toString();
    Intrinsics.checkExpressionValueIsNotNull(paramSequence, "joinTo(StringBuilder(), …ed, transform).toString()");
    return paramSequence;
  }
  
  public static final <T> T last(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Iterator localIterator = paramSequence.iterator();
    if (!localIterator.hasNext()) {
      throw ((Throwable)new NoSuchElementException("Sequence is empty."));
    }
    for (paramSequence = localIterator.next(); localIterator.hasNext(); paramSequence = localIterator.next()) {}
    return paramSequence;
  }
  
  public static final <T> T last(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    Iterator localIterator = paramSequence.iterator();
    paramSequence = null;
    int i = 0;
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (((Boolean)paramFunction1.invoke(localObject)).booleanValue())
      {
        i = 1;
        paramSequence = localObject;
      }
    }
    if (i == 0) {
      throw ((Throwable)new NoSuchElementException("Sequence contains no element matching the predicate."));
    }
    return paramSequence;
  }
  
  public static final <T> int lastIndexOf(@NotNull Sequence<? extends T> paramSequence, T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    int j = -1;
    int i = 0;
    while (paramSequence.hasNext())
    {
      if (Intrinsics.areEqual(paramT, paramSequence.next())) {
        j = i;
      }
      i += 1;
    }
    return j;
  }
  
  @Nullable
  public static final <T> T lastOrNull(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Iterator localIterator = paramSequence.iterator();
    if (!localIterator.hasNext()) {
      return null;
    }
    for (paramSequence = localIterator.next(); localIterator.hasNext(); paramSequence = localIterator.next()) {}
    return paramSequence;
  }
  
  @Nullable
  public static final <T> T lastOrNull(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    Iterator localIterator = paramSequence.iterator();
    paramSequence = null;
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (((Boolean)paramFunction1.invoke(localObject)).booleanValue()) {
        paramSequence = localObject;
      }
    }
    return paramSequence;
  }
  
  @NotNull
  public static final <T, R> Sequence<R> map(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    return (Sequence)new TransformingSequence(paramSequence, paramFunction1);
  }
  
  @NotNull
  public static final <T, R> Sequence<R> mapIndexed(@NotNull Sequence<? extends T> paramSequence, @NotNull Function2<? super Integer, ? super T, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "transform");
    return (Sequence)new TransformingIndexedSequence(paramSequence, paramFunction2);
  }
  
  @NotNull
  public static final <T, R> Sequence<R> mapIndexedNotNull(@NotNull Sequence<? extends T> paramSequence, @NotNull Function2<? super Integer, ? super T, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "transform");
    return SequencesKt.filterNotNull((Sequence)new TransformingIndexedSequence(paramSequence, paramFunction2));
  }
  
  @NotNull
  public static final <T, R, C extends Collection<? super R>> C mapIndexedNotNullTo(@NotNull Sequence<? extends T> paramSequence, @NotNull C paramC, @NotNull Function2<? super Integer, ? super T, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "transform");
    paramSequence = paramSequence.iterator();
    int i = 0;
    while (paramSequence.hasNext())
    {
      Object localObject = paramFunction2.invoke(Integer.valueOf(i), paramSequence.next());
      if (localObject != null) {
        paramC.add(localObject);
      }
      i += 1;
    }
    return paramC;
  }
  
  @NotNull
  public static final <T, R, C extends Collection<? super R>> C mapIndexedTo(@NotNull Sequence<? extends T> paramSequence, @NotNull C paramC, @NotNull Function2<? super Integer, ? super T, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "transform");
    paramSequence = paramSequence.iterator();
    int j;
    for (int i = 0; paramSequence.hasNext(); i = j)
    {
      Object localObject = paramSequence.next();
      j = i + 1;
      paramC.add(paramFunction2.invoke(Integer.valueOf(i), localObject));
    }
    return paramC;
  }
  
  @NotNull
  public static final <T, R> Sequence<R> mapNotNull(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    return SequencesKt.filterNotNull((Sequence)new TransformingSequence(paramSequence, paramFunction1));
  }
  
  @NotNull
  public static final <T, R, C extends Collection<? super R>> C mapNotNullTo(@NotNull Sequence<? extends T> paramSequence, @NotNull C paramC, @NotNull Function1<? super T, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Object localObject = paramFunction1.invoke(paramSequence.next());
      if (localObject != null) {
        paramC.add(localObject);
      }
    }
    return paramC;
  }
  
  @NotNull
  public static final <T, R, C extends Collection<? super R>> C mapTo(@NotNull Sequence<? extends T> paramSequence, @NotNull C paramC, @NotNull Function1<? super T, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext()) {
      paramC.add(paramFunction1.invoke(paramSequence.next()));
    }
    return paramC;
  }
  
  @Nullable
  public static final <T extends Comparable<? super T>> T max(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Iterator localIterator = paramSequence.iterator();
    if (!localIterator.hasNext()) {
      return null;
    }
    paramSequence = (Comparable)localIterator.next();
    while (localIterator.hasNext())
    {
      Comparable localComparable = (Comparable)localIterator.next();
      if (paramSequence.compareTo(localComparable) < 0) {
        paramSequence = localComparable;
      }
    }
    return paramSequence;
  }
  
  @SinceKotlin(version="1.1")
  @Nullable
  public static final Double max(@NotNull Sequence<Double> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    if (!paramSequence.hasNext()) {
      return null;
    }
    double d2 = ((Number)paramSequence.next()).doubleValue();
    double d1 = d2;
    if (Double.isNaN(d2)) {
      return Double.valueOf(d2);
    }
    while (paramSequence.hasNext())
    {
      d2 = ((Number)paramSequence.next()).doubleValue();
      if (Double.isNaN(d2)) {
        return Double.valueOf(d2);
      }
      if (d1 < d2) {
        d1 = d2;
      }
    }
    return Double.valueOf(d1);
  }
  
  @SinceKotlin(version="1.1")
  @Nullable
  public static final Float max(@NotNull Sequence<Float> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    if (!paramSequence.hasNext()) {
      return null;
    }
    float f2 = ((Number)paramSequence.next()).floatValue();
    float f1 = f2;
    if (Float.isNaN(f2)) {
      return Float.valueOf(f2);
    }
    while (paramSequence.hasNext())
    {
      f2 = ((Number)paramSequence.next()).floatValue();
      if (Float.isNaN(f2)) {
        return Float.valueOf(f2);
      }
      if (f1 < f2) {
        f1 = f2;
      }
    }
    return Float.valueOf(f1);
  }
  
  @Nullable
  public static final <T, R extends Comparable<? super R>> T maxBy(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "selector");
    Iterator localIterator = paramSequence.iterator();
    if (!localIterator.hasNext()) {
      return null;
    }
    paramSequence = localIterator.next();
    Object localObject1 = (Comparable)paramFunction1.invoke(paramSequence);
    while (localIterator.hasNext())
    {
      Object localObject2 = localIterator.next();
      Comparable localComparable = (Comparable)paramFunction1.invoke(localObject2);
      if (((Comparable)localObject1).compareTo(localComparable) < 0)
      {
        paramSequence = localObject2;
        localObject1 = localComparable;
      }
    }
    return paramSequence;
  }
  
  @Nullable
  public static final <T> T maxWith(@NotNull Sequence<? extends T> paramSequence, @NotNull Comparator<? super T> paramComparator)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramComparator, "comparator");
    Iterator localIterator = paramSequence.iterator();
    if (!localIterator.hasNext()) {
      return null;
    }
    paramSequence = localIterator.next();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (paramComparator.compare(paramSequence, localObject) < 0) {
        paramSequence = localObject;
      }
    }
    return paramSequence;
  }
  
  @Nullable
  public static final <T extends Comparable<? super T>> T min(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Iterator localIterator = paramSequence.iterator();
    if (!localIterator.hasNext()) {
      return null;
    }
    paramSequence = (Comparable)localIterator.next();
    while (localIterator.hasNext())
    {
      Comparable localComparable = (Comparable)localIterator.next();
      if (paramSequence.compareTo(localComparable) > 0) {
        paramSequence = localComparable;
      }
    }
    return paramSequence;
  }
  
  @SinceKotlin(version="1.1")
  @Nullable
  public static final Double min(@NotNull Sequence<Double> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    if (!paramSequence.hasNext()) {
      return null;
    }
    double d2 = ((Number)paramSequence.next()).doubleValue();
    double d1 = d2;
    if (Double.isNaN(d2)) {
      return Double.valueOf(d2);
    }
    while (paramSequence.hasNext())
    {
      d2 = ((Number)paramSequence.next()).doubleValue();
      if (Double.isNaN(d2)) {
        return Double.valueOf(d2);
      }
      if (d1 > d2) {
        d1 = d2;
      }
    }
    return Double.valueOf(d1);
  }
  
  @SinceKotlin(version="1.1")
  @Nullable
  public static final Float min(@NotNull Sequence<Float> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    if (!paramSequence.hasNext()) {
      return null;
    }
    float f2 = ((Number)paramSequence.next()).floatValue();
    float f1 = f2;
    if (Float.isNaN(f2)) {
      return Float.valueOf(f2);
    }
    while (paramSequence.hasNext())
    {
      f2 = ((Number)paramSequence.next()).floatValue();
      if (Float.isNaN(f2)) {
        return Float.valueOf(f2);
      }
      if (f1 > f2) {
        f1 = f2;
      }
    }
    return Float.valueOf(f1);
  }
  
  @Nullable
  public static final <T, R extends Comparable<? super R>> T minBy(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "selector");
    Iterator localIterator = paramSequence.iterator();
    if (!localIterator.hasNext()) {
      return null;
    }
    paramSequence = localIterator.next();
    Object localObject1 = (Comparable)paramFunction1.invoke(paramSequence);
    while (localIterator.hasNext())
    {
      Object localObject2 = localIterator.next();
      Comparable localComparable = (Comparable)paramFunction1.invoke(localObject2);
      if (((Comparable)localObject1).compareTo(localComparable) > 0)
      {
        paramSequence = localObject2;
        localObject1 = localComparable;
      }
    }
    return paramSequence;
  }
  
  @Nullable
  public static final <T> T minWith(@NotNull Sequence<? extends T> paramSequence, @NotNull Comparator<? super T> paramComparator)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramComparator, "comparator");
    Iterator localIterator = paramSequence.iterator();
    if (!localIterator.hasNext()) {
      return null;
    }
    paramSequence = localIterator.next();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (paramComparator.compare(paramSequence, localObject) > 0) {
        paramSequence = localObject;
      }
    }
    return paramSequence;
  }
  
  @NotNull
  public static final <T> Sequence<T> minus(@NotNull Sequence<? extends T> paramSequence, @NotNull final Iterable<? extends T> paramIterable)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIterable, "elements");
    (Sequence)new Sequence()
    {
      @NotNull
      public Iterator<T> iterator()
      {
        Collection localCollection = CollectionsKt.convertToSetForSetOperation(paramIterable);
        if (localCollection.isEmpty()) {
          return receiver$0.iterator();
        }
        SequencesKt.filterNot(receiver$0, (Function1)new Lambda(localCollection)
        {
          public final boolean invoke(T paramAnonymous2T)
          {
            return $other.contains(paramAnonymous2T);
          }
        }).iterator();
      }
    };
  }
  
  @NotNull
  public static final <T> Sequence<T> minus(@NotNull Sequence<? extends T> paramSequence, final T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    (Sequence)new Sequence()
    {
      @NotNull
      public Iterator<T> iterator()
      {
        final Ref.BooleanRef localBooleanRef = new Ref.BooleanRef();
        element = false;
        SequencesKt.filter(receiver$0, (Function1)new Lambda(localBooleanRef)
        {
          public final boolean invoke(T paramAnonymous2T)
          {
            boolean bool3 = localBooleanRefelement;
            boolean bool2 = true;
            boolean bool1 = bool2;
            if (!bool3)
            {
              bool1 = bool2;
              if (Intrinsics.areEqual(paramAnonymous2T, this$0.$element))
              {
                localBooleanRefelement = true;
                bool1 = false;
              }
            }
            return bool1;
          }
        }).iterator();
      }
    };
  }
  
  @NotNull
  public static final <T> Sequence<T> minus(@NotNull Sequence<? extends T> paramSequence1, @NotNull final Sequence<? extends T> paramSequence2)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramSequence2, "elements");
    (Sequence)new Sequence()
    {
      @NotNull
      public Iterator<T> iterator()
      {
        HashSet localHashSet = SequencesKt.toHashSet(paramSequence2);
        if (localHashSet.isEmpty()) {
          return receiver$0.iterator();
        }
        SequencesKt.filterNot(receiver$0, (Function1)new Lambda(localHashSet)
        {
          public final boolean invoke(T paramAnonymous2T)
          {
            return $other.contains(paramAnonymous2T);
          }
        }).iterator();
      }
    };
  }
  
  @NotNull
  public static final <T> Sequence<T> minus(@NotNull Sequence<? extends T> paramSequence, @NotNull final T[] paramArrayOfT)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayOfT, "elements");
    int i;
    if (paramArrayOfT.length == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      return paramSequence;
    }
    (Sequence)new Sequence()
    {
      @NotNull
      public Iterator<T> iterator()
      {
        HashSet localHashSet = ArraysKt.toHashSet(paramArrayOfT);
        SequencesKt.filterNot(receiver$0, (Function1)new Lambda(localHashSet)
        {
          public final boolean invoke(T paramAnonymous2T)
          {
            return $other.contains(paramAnonymous2T);
          }
        }).iterator();
      }
    };
  }
  
  @InlineOnly
  private static final <T> Sequence<T> minusElement(@NotNull Sequence<? extends T> paramSequence, T paramT)
  {
    return SequencesKt.minus(paramSequence, paramT);
  }
  
  public static final <T> boolean none(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return paramSequence.iterator().hasNext() ^ true;
  }
  
  public static final <T> boolean none(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext()) {
      if (((Boolean)paramFunction1.invoke(paramSequence.next())).booleanValue()) {
        return false;
      }
    }
    return true;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T> Sequence<T> onEach(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Unit> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "action");
    SequencesKt.map(paramSequence, (Function1)new Lambda(paramFunction1)
    {
      public final T invoke(T paramAnonymousT)
      {
        $action.invoke(paramAnonymousT);
        return paramAnonymousT;
      }
    });
  }
  
  @NotNull
  public static final <T> Pair<List<T>, List<T>> partition(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Object localObject = paramSequence.next();
      if (((Boolean)paramFunction1.invoke(localObject)).booleanValue()) {
        localArrayList1.add(localObject);
      } else {
        localArrayList2.add(localObject);
      }
    }
    return new Pair(localArrayList1, localArrayList2);
  }
  
  @NotNull
  public static final <T> Sequence<T> plus(@NotNull Sequence<? extends T> paramSequence, @NotNull Iterable<? extends T> paramIterable)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIterable, "elements");
    return SequencesKt.flatten(SequencesKt.sequenceOf(new Sequence[] { paramSequence, CollectionsKt.asSequence(paramIterable) }));
  }
  
  @NotNull
  public static final <T> Sequence<T> plus(@NotNull Sequence<? extends T> paramSequence, T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return SequencesKt.flatten(SequencesKt.sequenceOf(new Sequence[] { paramSequence, SequencesKt.sequenceOf(new Object[] { paramT }) }));
  }
  
  @NotNull
  public static final <T> Sequence<T> plus(@NotNull Sequence<? extends T> paramSequence1, @NotNull Sequence<? extends T> paramSequence2)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramSequence2, "elements");
    return SequencesKt.flatten(SequencesKt.sequenceOf(new Sequence[] { paramSequence1, paramSequence2 }));
  }
  
  @NotNull
  public static final <T> Sequence<T> plus(@NotNull Sequence<? extends T> paramSequence, @NotNull T[] paramArrayOfT)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayOfT, "elements");
    return SequencesKt.plus(paramSequence, (Iterable)ArraysKt.asList(paramArrayOfT));
  }
  
  @InlineOnly
  private static final <T> Sequence<T> plusElement(@NotNull Sequence<? extends T> paramSequence, T paramT)
  {
    return SequencesKt.plus(paramSequence, paramT);
  }
  
  public static final <S, T extends S> S reduce(@NotNull Sequence<? extends T> paramSequence, @NotNull Function2<? super S, ? super T, ? extends S> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
    Iterator localIterator = paramSequence.iterator();
    if (!localIterator.hasNext()) {
      throw ((Throwable)new UnsupportedOperationException("Empty sequence can't be reduced."));
    }
    for (paramSequence = localIterator.next(); localIterator.hasNext(); paramSequence = paramFunction2.invoke(paramSequence, localIterator.next())) {}
    return paramSequence;
  }
  
  public static final <S, T extends S> S reduceIndexed(@NotNull Sequence<? extends T> paramSequence, @NotNull Function3<? super Integer, ? super S, ? super T, ? extends S> paramFunction3)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction3, "operation");
    Iterator localIterator = paramSequence.iterator();
    if (!localIterator.hasNext()) {
      throw ((Throwable)new UnsupportedOperationException("Empty sequence can't be reduced."));
    }
    paramSequence = localIterator.next();
    int j;
    for (int i = 1; localIterator.hasNext(); i = j)
    {
      j = i + 1;
      paramSequence = paramFunction3.invoke(Integer.valueOf(i), paramSequence, localIterator.next());
    }
    return paramSequence;
  }
  
  @NotNull
  public static final <T> Sequence<T> requireNoNulls(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    SequencesKt.map(paramSequence, (Function1)new Lambda(paramSequence)
    {
      @NotNull
      public final T invoke(@Nullable T paramAnonymousT)
      {
        if (paramAnonymousT != null) {
          return paramAnonymousT;
        }
        paramAnonymousT = new StringBuilder();
        paramAnonymousT.append("null element found in ");
        paramAnonymousT.append(receiver$0);
        paramAnonymousT.append('.');
        throw ((Throwable)new IllegalArgumentException(paramAnonymousT.toString()));
      }
    });
  }
  
  public static final <T> T single(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    if (!paramSequence.hasNext()) {
      throw ((Throwable)new NoSuchElementException("Sequence is empty."));
    }
    Object localObject = paramSequence.next();
    if (paramSequence.hasNext()) {
      throw ((Throwable)new IllegalArgumentException("Sequence has more than one element."));
    }
    return localObject;
  }
  
  public static final <T> T single(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    Iterator localIterator = paramSequence.iterator();
    paramSequence = null;
    int i = 0;
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (((Boolean)paramFunction1.invoke(localObject)).booleanValue())
      {
        if (i != 0) {
          throw ((Throwable)new IllegalArgumentException("Sequence contains more than one matching element."));
        }
        i = 1;
        paramSequence = localObject;
      }
    }
    if (i == 0) {
      throw ((Throwable)new NoSuchElementException("Sequence contains no element matching the predicate."));
    }
    return paramSequence;
  }
  
  @Nullable
  public static final <T> T singleOrNull(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    if (!paramSequence.hasNext()) {
      return null;
    }
    Object localObject = paramSequence.next();
    if (paramSequence.hasNext()) {
      return null;
    }
    return localObject;
  }
  
  @Nullable
  public static final <T> T singleOrNull(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    Iterator localIterator = paramSequence.iterator();
    int i = 0;
    paramSequence = null;
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (((Boolean)paramFunction1.invoke(localObject)).booleanValue())
      {
        if (i != 0) {
          return null;
        }
        i = 1;
        paramSequence = localObject;
      }
    }
    if (i == 0) {
      return null;
    }
    return paramSequence;
  }
  
  @NotNull
  public static final <T extends Comparable<? super T>> Sequence<T> sorted(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    (Sequence)new Sequence()
    {
      @NotNull
      public Iterator<T> iterator()
      {
        List localList = SequencesKt.toMutableList(receiver$0);
        CollectionsKt.sort(localList);
        return localList.iterator();
      }
    };
  }
  
  @NotNull
  public static final <T, R extends Comparable<? super R>> Sequence<T> sortedBy(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "selector");
    return SequencesKt.sortedWith(paramSequence, (Comparator)new ComparisonsKt__ComparisonsKt.compareBy.2(paramFunction1));
  }
  
  @NotNull
  public static final <T, R extends Comparable<? super R>> Sequence<T> sortedByDescending(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "selector");
    return SequencesKt.sortedWith(paramSequence, (Comparator)new ComparisonsKt__ComparisonsKt.compareByDescending.1(paramFunction1));
  }
  
  @NotNull
  public static final <T extends Comparable<? super T>> Sequence<T> sortedDescending(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return SequencesKt.sortedWith(paramSequence, ComparisonsKt.reverseOrder());
  }
  
  @NotNull
  public static final <T> Sequence<T> sortedWith(@NotNull Sequence<? extends T> paramSequence, @NotNull final Comparator<? super T> paramComparator)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramComparator, "comparator");
    (Sequence)new Sequence()
    {
      @NotNull
      public Iterator<T> iterator()
      {
        List localList = SequencesKt.toMutableList(receiver$0);
        CollectionsKt.sortWith(localList, paramComparator);
        return localList.iterator();
      }
    };
  }
  
  public static final <T> int sumBy(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Integer> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "selector");
    paramSequence = paramSequence.iterator();
    int i = 0;
    while (paramSequence.hasNext()) {
      i += ((Number)paramFunction1.invoke(paramSequence.next())).intValue();
    }
    return i;
  }
  
  public static final <T> double sumByDouble(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Double> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "selector");
    paramSequence = paramSequence.iterator();
    for (double d = 0.0D; paramSequence.hasNext(); d += ((Number)paramFunction1.invoke(paramSequence.next())).doubleValue()) {}
    return d;
  }
  
  @JvmName(name="sumOfByte")
  public static final int sumOfByte(@NotNull Sequence<Byte> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    int i = 0;
    while (paramSequence.hasNext()) {
      i += ((Number)paramSequence.next()).byteValue();
    }
    return i;
  }
  
  @JvmName(name="sumOfDouble")
  public static final double sumOfDouble(@NotNull Sequence<Double> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    for (double d = 0.0D; paramSequence.hasNext(); d += ((Number)paramSequence.next()).doubleValue()) {}
    return d;
  }
  
  @JvmName(name="sumOfFloat")
  public static final float sumOfFloat(@NotNull Sequence<Float> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    for (float f = 0.0F; paramSequence.hasNext(); f += ((Number)paramSequence.next()).floatValue()) {}
    return f;
  }
  
  @JvmName(name="sumOfInt")
  public static final int sumOfInt(@NotNull Sequence<Integer> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    int i = 0;
    while (paramSequence.hasNext()) {
      i += ((Number)paramSequence.next()).intValue();
    }
    return i;
  }
  
  @JvmName(name="sumOfLong")
  public static final long sumOfLong(@NotNull Sequence<Long> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    for (long l = 0L; paramSequence.hasNext(); l += ((Number)paramSequence.next()).longValue()) {}
    return l;
  }
  
  @JvmName(name="sumOfShort")
  public static final int sumOfShort(@NotNull Sequence<Short> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    paramSequence = paramSequence.iterator();
    int i = 0;
    while (paramSequence.hasNext()) {
      i += ((Number)paramSequence.next()).shortValue();
    }
    return i;
  }
  
  @NotNull
  public static final <T> Sequence<T> take(@NotNull Sequence<? extends T> paramSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    int i;
    if (paramInt >= 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      paramSequence = new StringBuilder();
      paramSequence.append("Requested element count ");
      paramSequence.append(paramInt);
      paramSequence.append(" is less than zero.");
      throw ((Throwable)new IllegalArgumentException(paramSequence.toString().toString()));
    }
    if (paramInt == 0) {
      return SequencesKt.emptySequence();
    }
    if ((paramSequence instanceof DropTakeSequence)) {
      return ((DropTakeSequence)paramSequence).take(paramInt);
    }
    return (Sequence)new TakeSequence(paramSequence, paramInt);
  }
  
  @NotNull
  public static final <T> Sequence<T> takeWhile(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    return (Sequence)new TakeWhileSequence(paramSequence, paramFunction1);
  }
  
  @NotNull
  public static final <T, C extends Collection<? super T>> C toCollection(@NotNull Sequence<? extends T> paramSequence, @NotNull C paramC)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramC, "destination");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext()) {
      paramC.add(paramSequence.next());
    }
    return paramC;
  }
  
  @NotNull
  public static final <T> HashSet<T> toHashSet(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return (HashSet)SequencesKt.toCollection(paramSequence, (Collection)new HashSet());
  }
  
  @NotNull
  public static final <T> List<T> toList(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return CollectionsKt.optimizeReadOnlyList(SequencesKt.toMutableList(paramSequence));
  }
  
  @NotNull
  public static final <T> List<T> toMutableList(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return (List)SequencesKt.toCollection(paramSequence, (Collection)new ArrayList());
  }
  
  @NotNull
  public static final <T> Set<T> toMutableSet(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    LinkedHashSet localLinkedHashSet = new LinkedHashSet();
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext()) {
      localLinkedHashSet.add(paramSequence.next());
    }
    return (Set)localLinkedHashSet;
  }
  
  @NotNull
  public static final <T> Set<T> toSet(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return SetsKt.optimizeReadOnlySet((Set)SequencesKt.toCollection(paramSequence, (Collection)new LinkedHashSet()));
  }
  
  @NotNull
  public static final <T extends Comparable<? super T>> SortedSet<T> toSortedSet(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return (SortedSet)SequencesKt.toCollection(paramSequence, (Collection)new TreeSet());
  }
  
  @NotNull
  public static final <T> SortedSet<T> toSortedSet(@NotNull Sequence<? extends T> paramSequence, @NotNull Comparator<? super T> paramComparator)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramComparator, "comparator");
    return (SortedSet)SequencesKt.toCollection(paramSequence, (Collection)new TreeSet(paramComparator));
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final <T> Sequence<List<T>> windowed(@NotNull Sequence<? extends T> paramSequence, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return SlidingWindowKt.windowedSequence(paramSequence, paramInt1, paramInt2, paramBoolean, false);
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final <T, R> Sequence<R> windowed(@NotNull Sequence<? extends T> paramSequence, int paramInt1, int paramInt2, boolean paramBoolean, @NotNull Function1<? super List<? extends T>, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    return SequencesKt.map(SlidingWindowKt.windowedSequence(paramSequence, paramInt1, paramInt2, paramBoolean, true), paramFunction1);
  }
  
  @NotNull
  public static final <T> Sequence<IndexedValue<T>> withIndex(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return (Sequence)new IndexingSequence(paramSequence);
  }
  
  @NotNull
  public static final <T, R> Sequence<Pair<T, R>> zip(@NotNull Sequence<? extends T> paramSequence, @NotNull Sequence<? extends R> paramSequence1)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramSequence1, "other");
    return (Sequence)new MergingSequence(paramSequence, paramSequence1, (Function2)zip.1.INSTANCE);
  }
  
  @NotNull
  public static final <T, R, V> Sequence<V> zip(@NotNull Sequence<? extends T> paramSequence, @NotNull Sequence<? extends R> paramSequence1, @NotNull Function2<? super T, ? super R, ? extends V> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramSequence1, "other");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "transform");
    return (Sequence)new MergingSequence(paramSequence, paramSequence1, paramFunction2);
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final <T> Sequence<Pair<T, T>> zipWithNext(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return SequencesKt.zipWithNext(paramSequence, (Function2)zipWithNext.1.INSTANCE);
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final <T, R> Sequence<R> zipWithNext(@NotNull Sequence<? extends T> paramSequence, @NotNull final Function2<? super T, ? super T, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "transform");
    SequenceBuilderKt.buildSequence((Function2)new CoroutineImpl(paramSequence, paramFunction2)
    {
      Object L$0;
      Object L$1;
      Object L$2;
      Object L$3;
      private SequenceBuilder p$;
      
      @NotNull
      public final Continuation<Unit> create(@NotNull SequenceBuilder<? super R> paramAnonymousSequenceBuilder, @NotNull Continuation<? super Unit> paramAnonymousContinuation)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousSequenceBuilder, "$receiver");
        Intrinsics.checkParameterIsNotNull(paramAnonymousContinuation, "continuation");
        paramAnonymousContinuation = new 2(receiver$0, paramFunction2, paramAnonymousContinuation);
        p$ = paramAnonymousSequenceBuilder;
        return paramAnonymousContinuation;
      }
      
      @Nullable
      public final Object doResume(@Nullable Object paramAnonymousObject, @Nullable Throwable paramAnonymousThrowable)
      {
        Object localObject3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Object localObject1;
        Object localObject2;
        switch (label)
        {
        default: 
          throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        case 1: 
          paramAnonymousObject = L$3;
          localObject1 = L$2;
          localObject2 = (Iterator)L$1;
          localObject1 = (SequenceBuilder)L$0;
          if (paramAnonymousThrowable != null) {
            throw paramAnonymousThrowable;
          }
          paramAnonymousThrowable = (Throwable)localObject2;
          break;
        case 0: 
          if (paramAnonymousThrowable != null) {
            throw paramAnonymousThrowable;
          }
          localObject1 = p$;
          paramAnonymousThrowable = receiver$0.iterator();
          if (!paramAnonymousThrowable.hasNext()) {
            return Unit.INSTANCE;
          }
          break;
        }
        for (paramAnonymousObject = paramAnonymousThrowable.next(); paramAnonymousThrowable.hasNext(); paramAnonymousObject = localObject2)
        {
          localObject2 = paramAnonymousThrowable.next();
          Object localObject4 = paramFunction2.invoke(paramAnonymousObject, localObject2);
          L$0 = localObject1;
          L$1 = paramAnonymousThrowable;
          L$2 = paramAnonymousObject;
          L$3 = localObject2;
          label = 1;
          paramAnonymousObject = ((SequenceBuilder)localObject1).yield(localObject4, this);
          InlineMarker.mark(2);
          if (paramAnonymousObject == localObject3) {
            return localObject3;
          }
        }
        return Unit.INSTANCE;
      }
      
      @Nullable
      public final Object invoke(@NotNull SequenceBuilder<? super R> paramAnonymousSequenceBuilder, @NotNull Continuation<? super Unit> paramAnonymousContinuation)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousSequenceBuilder, "$receiver");
        Intrinsics.checkParameterIsNotNull(paramAnonymousContinuation, "continuation");
        return ((2)create(paramAnonymousSequenceBuilder, paramAnonymousContinuation)).doResume(Unit.INSTANCE, null);
      }
    });
  }
}
