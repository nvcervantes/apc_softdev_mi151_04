package kotlin.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000~\n\000\n\002\020\b\n\000\n\002\020$\n\002\b\003\n\002\030\002\n\002\030\002\n\000\n\002\020\021\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\007\n\002\020%\n\000\n\002\020&\n\002\b\003\n\002\020\013\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\f\n\002\030\002\n\002\b\006\n\002\020(\n\002\020)\n\002\020'\n\002\b\013\n\002\020\034\n\002\030\002\n\000\n\002\020\002\n\002\b\027\032\036\020\002\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005\0321\020\006\032\036\022\004\022\002H\004\022\004\022\002H\0050\007j\016\022\004\022\002H\004\022\004\022\002H\005`\b\"\004\b\000\020\004\"\004\b\001\020\005H\b\032_\020\006\032\036\022\004\022\002H\004\022\004\022\002H\0050\007j\016\022\004\022\002H\004\022\004\022\002H\005`\b\"\004\b\000\020\004\"\004\b\001\020\0052*\020\t\032\026\022\022\b\001\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130\n\"\016\022\004\022\002H\004\022\004\022\002H\0050\013¢\006\002\020\f\0321\020\r\032\036\022\004\022\002H\004\022\004\022\002H\0050\016j\016\022\004\022\002H\004\022\004\022\002H\005`\017\"\004\b\000\020\004\"\004\b\001\020\005H\b\032_\020\r\032\036\022\004\022\002H\004\022\004\022\002H\0050\016j\016\022\004\022\002H\004\022\004\022\002H\005`\017\"\004\b\000\020\004\"\004\b\001\020\0052*\020\t\032\026\022\022\b\001\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130\n\"\016\022\004\022\002H\004\022\004\022\002H\0050\013¢\006\002\020\020\032\020\020\021\032\0020\0012\006\020\022\032\0020\001H\001\032!\020\023\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005H\b\032O\020\023\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\0052*\020\t\032\026\022\022\b\001\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130\n\"\016\022\004\022\002H\004\022\004\022\002H\0050\013¢\006\002\020\024\0324\020\023\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\0052\022\020\025\032\016\022\004\022\002H\004\022\004\022\002H\0050\013H\007\032!\020\026\032\016\022\004\022\002H\004\022\004\022\002H\0050\027\"\004\b\000\020\004\"\004\b\001\020\005H\b\032O\020\026\032\016\022\004\022\002H\004\022\004\022\002H\0050\027\"\004\b\000\020\004\"\004\b\001\020\0052*\020\t\032\026\022\022\b\001\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130\n\"\016\022\004\022\002H\004\022\004\022\002H\0050\013¢\006\002\020\024\032*\020\030\032\002H\004\"\004\b\000\020\004\"\004\b\001\020\005*\016\022\004\022\002H\004\022\004\022\002H\0050\031H\n¢\006\002\020\032\032*\020\033\032\002H\005\"\004\b\000\020\004\"\004\b\001\020\005*\016\022\004\022\002H\004\022\004\022\002H\0050\031H\n¢\006\002\020\032\0329\020\034\032\0020\035\"\t\b\000\020\004¢\006\002\b\036\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\006\020\037\032\002H\004H\n¢\006\002\020 \0321\020!\032\0020\035\"\t\b\000\020\004¢\006\002\b\036*\016\022\006\b\001\022\002H\004\022\002\b\0030\0032\006\020\037\032\002H\004H\b¢\006\002\020 \0327\020\"\032\0020\035\"\004\b\000\020\004\"\t\b\001\020\005¢\006\002\b\036*\016\022\004\022\002H\004\022\004\022\002H\0050\0032\006\020#\032\002H\005H\b¢\006\002\020 \032S\020$\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\036\020%\032\032\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\031\022\004\022\0020\0350&H\b\032G\020'\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\022\020%\032\016\022\004\022\002H\004\022\004\022\0020\0350&H\b\032S\020(\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\036\020%\032\032\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\031\022\004\022\0020\0350&H\b\032n\020)\032\002H*\"\004\b\000\020\004\"\004\b\001\020\005\"\030\b\002\020**\022\022\006\b\000\022\002H\004\022\006\b\000\022\002H\0050\027*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\006\020+\032\002H*2\036\020%\032\032\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\031\022\004\022\0020\0350&H\b¢\006\002\020,\032n\020-\032\002H*\"\004\b\000\020\004\"\004\b\001\020\005\"\030\b\002\020**\022\022\006\b\000\022\002H\004\022\006\b\000\022\002H\0050\027*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\006\020+\032\002H*2\036\020%\032\032\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\031\022\004\022\0020\0350&H\b¢\006\002\020,\032G\020.\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\022\020%\032\016\022\004\022\002H\005\022\004\022\0020\0350&H\b\032;\020/\032\004\030\001H\005\"\t\b\000\020\004¢\006\002\b\036\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\006\020\037\032\002H\004H\n¢\006\002\0200\032@\0201\032\002H\005\"\004\b\000\020\004\"\004\b\001\020\005*\016\022\004\022\002H\004\022\004\022\002H\0050\0032\006\020\037\032\002H\0042\f\0202\032\b\022\004\022\002H\00503H\b¢\006\002\0204\032@\0205\032\002H\005\"\004\b\000\020\004\"\004\b\001\020\005*\016\022\004\022\002H\004\022\004\022\002H\0050\0032\006\020\037\032\002H\0042\f\0202\032\b\022\004\022\002H\00503H\b¢\006\002\0204\032@\0206\032\002H\005\"\004\b\000\020\004\"\004\b\001\020\005*\016\022\004\022\002H\004\022\004\022\002H\0050\0272\006\020\037\032\002H\0042\f\0202\032\b\022\004\022\002H\00503H\b¢\006\002\0204\0321\0207\032\002H\005\"\004\b\000\020\004\"\004\b\001\020\005*\016\022\004\022\002H\004\022\004\022\002H\0050\0032\006\020\037\032\002H\004H\007¢\006\002\0200\032'\0208\032\0020\035\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\003H\b\0329\0209\032\024\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\0310:\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\003H\n\032<\0209\032\024\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050<0;\"\004\b\000\020\004\"\004\b\001\020\005*\016\022\004\022\002H\004\022\004\022\002H\0050\027H\n¢\006\002\b=\032Y\020>\032\016\022\004\022\002H?\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005\"\004\b\002\020?*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\036\020@\032\032\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\031\022\004\022\002H?0&H\b\032t\020A\032\002H*\"\004\b\000\020\004\"\004\b\001\020\005\"\004\b\002\020?\"\030\b\003\020**\022\022\006\b\000\022\002H?\022\006\b\000\022\002H\0050\027*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\006\020+\032\002H*2\036\020@\032\032\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\031\022\004\022\002H?0&H\b¢\006\002\020,\032Y\020B\032\016\022\004\022\002H\004\022\004\022\002H?0\003\"\004\b\000\020\004\"\004\b\001\020\005\"\004\b\002\020?*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\036\020@\032\032\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\031\022\004\022\002H?0&H\b\032t\020C\032\002H*\"\004\b\000\020\004\"\004\b\001\020\005\"\004\b\002\020?\"\030\b\003\020**\022\022\006\b\000\022\002H\004\022\006\b\000\022\002H?0\027*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\006\020+\032\002H*2\036\020@\032\032\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\031\022\004\022\002H?0&H\b¢\006\002\020,\032@\020D\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\006\020\037\032\002H\004H\002¢\006\002\020E\032H\020D\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\016\020F\032\n\022\006\b\001\022\002H\0040\nH\002¢\006\002\020G\032A\020D\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\f\020F\032\b\022\004\022\002H\0040HH\002\032A\020D\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\f\020F\032\b\022\004\022\002H\0040IH\002\0322\020J\032\0020K\"\004\b\000\020\004\"\004\b\001\020\005*\016\022\004\022\002H\004\022\004\022\002H\0050\0272\006\020\037\032\002H\004H\n¢\006\002\020L\032:\020J\032\0020K\"\004\b\000\020\004\"\004\b\001\020\005*\016\022\004\022\002H\004\022\004\022\002H\0050\0272\016\020F\032\n\022\006\b\001\022\002H\0040\nH\n¢\006\002\020M\0323\020J\032\0020K\"\004\b\000\020\004\"\004\b\001\020\005*\016\022\004\022\002H\004\022\004\022\002H\0050\0272\f\020F\032\b\022\004\022\002H\0040HH\n\0323\020J\032\0020K\"\004\b\000\020\004\"\004\b\001\020\005*\016\022\004\022\002H\004\022\004\022\002H\0050\0272\f\020F\032\b\022\004\022\002H\0040IH\n\0320\020N\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\016\022\004\022\002H\004\022\004\022\002H\0050\003H\000\0323\020O\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\004\022\002H\004\022\004\022\002H\005\030\0010\003H\b\032T\020P\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\032\020\t\032\026\022\022\b\001\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130\nH\002¢\006\002\020Q\032G\020P\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\022\020\025\032\016\022\004\022\002H\004\022\004\022\002H\0050\013H\002\032M\020P\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\030\020\t\032\024\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130HH\002\032I\020P\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\024\020R\032\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\003H\002\032M\020P\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\030\020\t\032\024\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130IH\002\032J\020S\032\0020K\"\004\b\000\020\004\"\004\b\001\020\005*\022\022\006\b\000\022\002H\004\022\006\b\000\022\002H\0050\0272\032\020\t\032\026\022\022\b\001\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130\nH\n¢\006\002\020T\032=\020S\032\0020K\"\004\b\000\020\004\"\004\b\001\020\005*\022\022\006\b\000\022\002H\004\022\006\b\000\022\002H\0050\0272\022\020\025\032\016\022\004\022\002H\004\022\004\022\002H\0050\013H\n\032C\020S\032\0020K\"\004\b\000\020\004\"\004\b\001\020\005*\022\022\006\b\000\022\002H\004\022\006\b\000\022\002H\0050\0272\030\020\t\032\024\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130HH\n\032=\020S\032\0020K\"\004\b\000\020\004\"\004\b\001\020\005*\022\022\006\b\000\022\002H\004\022\006\b\000\022\002H\0050\0272\022\020R\032\016\022\004\022\002H\004\022\004\022\002H\0050\003H\n\032C\020S\032\0020K\"\004\b\000\020\004\"\004\b\001\020\005*\022\022\006\b\000\022\002H\004\022\006\b\000\022\002H\0050\0272\030\020\t\032\024\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130IH\n\032G\020U\032\0020K\"\004\b\000\020\004\"\004\b\001\020\005*\022\022\006\b\000\022\002H\004\022\006\b\000\022\002H\0050\0272\032\020\t\032\026\022\022\b\001\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130\n¢\006\002\020T\032@\020U\032\0020K\"\004\b\000\020\004\"\004\b\001\020\005*\022\022\006\b\000\022\002H\004\022\006\b\000\022\002H\0050\0272\030\020\t\032\024\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130H\032@\020U\032\0020K\"\004\b\000\020\004\"\004\b\001\020\005*\022\022\006\b\000\022\002H\004\022\006\b\000\022\002H\0050\0272\030\020\t\032\024\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130I\032;\020V\032\004\030\001H\005\"\t\b\000\020\004¢\006\002\b\036\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0272\006\020\037\032\002H\004H\b¢\006\002\0200\032:\020W\032\0020K\"\004\b\000\020\004\"\004\b\001\020\005*\016\022\004\022\002H\004\022\004\022\002H\0050\0272\006\020\037\032\002H\0042\006\020#\032\002H\005H\n¢\006\002\020X\032;\020Y\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\026\022\022\b\001\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130\n¢\006\002\020\024\032Q\020Y\032\002H*\"\004\b\000\020\004\"\004\b\001\020\005\"\030\b\002\020**\022\022\006\b\000\022\002H\004\022\006\b\000\022\002H\0050\027*\026\022\022\b\001\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130\n2\006\020+\032\002H*¢\006\002\020Z\0324\020Y\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\024\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130H\032O\020Y\032\002H*\"\004\b\000\020\004\"\004\b\001\020\005\"\030\b\002\020**\022\022\006\b\000\022\002H\004\022\006\b\000\022\002H\0050\027*\024\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130H2\006\020+\032\002H*¢\006\002\020[\0322\020Y\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\003H\007\032M\020Y\032\002H*\"\004\b\000\020\004\"\004\b\001\020\005\"\030\b\002\020**\022\022\006\b\000\022\002H\004\022\006\b\000\022\002H\0050\027*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\0032\006\020+\032\002H*H\007¢\006\002\020\\\0324\020Y\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\024\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130I\032O\020Y\032\002H*\"\004\b\000\020\004\"\004\b\001\020\005\"\030\b\002\020**\022\022\006\b\000\022\002H\004\022\006\b\000\022\002H\0050\027*\024\022\020\022\016\022\004\022\002H\004\022\004\022\002H\0050\0130I2\006\020+\032\002H*¢\006\002\020]\0322\020^\032\016\022\004\022\002H\004\022\004\022\002H\0050\027\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\003H\007\0321\020_\032\016\022\004\022\002H\004\022\004\022\002H\0050\013\"\004\b\000\020\004\"\004\b\001\020\005*\016\022\004\022\002H\004\022\004\022\002H\0050\031H\b\0322\020`\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\020\022\006\b\001\022\002H\004\022\004\022\002H\0050\003H\001\0321\020a\032\016\022\004\022\002H\004\022\004\022\002H\0050\003\"\004\b\000\020\004\"\004\b\001\020\005*\016\022\004\022\002H\004\022\004\022\002H\0050\003H\b\"\016\020\000\032\0020\001XT¢\006\002\n\000¨\006b"}, d2={"INT_MAX_POWER_OF_TWO", "", "emptyMap", "", "K", "V", "hashMapOf", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "pairs", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)Ljava/util/HashMap;", "linkedMapOf", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "([Lkotlin/Pair;)Ljava/util/LinkedHashMap;", "mapCapacity", "expectedSize", "mapOf", "([Lkotlin/Pair;)Ljava/util/Map;", "pair", "mutableMapOf", "", "component1", "", "(Ljava/util/Map$Entry;)Ljava/lang/Object;", "component2", "contains", "", "Lkotlin/internal/OnlyInputTypes;", "key", "(Ljava/util/Map;Ljava/lang/Object;)Z", "containsKey", "containsValue", "value", "filter", "predicate", "Lkotlin/Function1;", "filterKeys", "filterNot", "filterNotTo", "M", "destination", "(Ljava/util/Map;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "filterTo", "filterValues", "get", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getOrElseNullable", "getOrPut", "getValue", "isNotEmpty", "iterator", "", "", "", "mutableIterator", "mapKeys", "R", "transform", "mapKeysTo", "mapValues", "mapValuesTo", "minus", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/util/Map;", "keys", "(Ljava/util/Map;[Ljava/lang/Object;)Ljava/util/Map;", "", "Lkotlin/sequences/Sequence;", "minusAssign", "", "(Ljava/util/Map;Ljava/lang/Object;)V", "(Ljava/util/Map;[Ljava/lang/Object;)V", "optimizeReadOnlyMap", "orEmpty", "plus", "(Ljava/util/Map;[Lkotlin/Pair;)Ljava/util/Map;", "map", "plusAssign", "(Ljava/util/Map;[Lkotlin/Pair;)V", "putAll", "remove", "set", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)V", "toMap", "([Lkotlin/Pair;Ljava/util/Map;)Ljava/util/Map;", "(Ljava/lang/Iterable;Ljava/util/Map;)Ljava/util/Map;", "(Ljava/util/Map;Ljava/util/Map;)Ljava/util/Map;", "(Lkotlin/sequences/Sequence;Ljava/util/Map;)Ljava/util/Map;", "toMutableMap", "toPair", "toSingletonMap", "toSingletonMapOrSelf", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/collections/MapsKt")
class MapsKt__MapsKt
  extends MapsKt__MapsJVMKt
{
  private static final int INT_MAX_POWER_OF_TWO = 1073741824;
  
  public MapsKt__MapsKt() {}
  
  @InlineOnly
  private static final <K, V> K component1(@NotNull Map.Entry<? extends K, ? extends V> paramEntry)
  {
    Intrinsics.checkParameterIsNotNull(paramEntry, "$receiver");
    return paramEntry.getKey();
  }
  
  @InlineOnly
  private static final <K, V> V component2(@NotNull Map.Entry<? extends K, ? extends V> paramEntry)
  {
    Intrinsics.checkParameterIsNotNull(paramEntry, "$receiver");
    return paramEntry.getValue();
  }
  
  @InlineOnly
  private static final <K, V> boolean contains(@NotNull Map<? extends K, ? extends V> paramMap, K paramK)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    return paramMap.containsKey(paramK);
  }
  
  @InlineOnly
  private static final <K> boolean containsKey(@NotNull Map<? extends K, ?> paramMap, K paramK)
  {
    if (paramMap == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
    }
    return paramMap.containsKey(paramK);
  }
  
  @InlineOnly
  private static final <K, V> boolean containsValue(@NotNull Map<K, ? extends V> paramMap, V paramV)
  {
    return paramMap.containsValue(paramV);
  }
  
  @NotNull
  public static final <K, V> Map<K, V> emptyMap()
  {
    EmptyMap localEmptyMap = EmptyMap.INSTANCE;
    if (localEmptyMap == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
    }
    return (Map)localEmptyMap;
  }
  
  @NotNull
  public static final <K, V> Map<K, V> filter(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    Map localMap = (Map)new LinkedHashMap();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      if (((Boolean)paramFunction1.invoke(localEntry)).booleanValue()) {
        localMap.put(localEntry.getKey(), localEntry.getValue());
      }
    }
    return localMap;
  }
  
  @NotNull
  public static final <K, V> Map<K, V> filterKeys(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull Function1<? super K, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      if (((Boolean)paramFunction1.invoke(localEntry.getKey())).booleanValue()) {
        localLinkedHashMap.put(localEntry.getKey(), localEntry.getValue());
      }
    }
    return (Map)localLinkedHashMap;
  }
  
  @NotNull
  public static final <K, V> Map<K, V> filterNot(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    Map localMap = (Map)new LinkedHashMap();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      if (!((Boolean)paramFunction1.invoke(localEntry)).booleanValue()) {
        localMap.put(localEntry.getKey(), localEntry.getValue());
      }
    }
    return localMap;
  }
  
  @NotNull
  public static final <K, V, M extends Map<? super K, ? super V>> M filterNotTo(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull M paramM, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      if (!((Boolean)paramFunction1.invoke(localEntry)).booleanValue()) {
        paramM.put(localEntry.getKey(), localEntry.getValue());
      }
    }
    return paramM;
  }
  
  @NotNull
  public static final <K, V, M extends Map<? super K, ? super V>> M filterTo(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull M paramM, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      if (((Boolean)paramFunction1.invoke(localEntry)).booleanValue()) {
        paramM.put(localEntry.getKey(), localEntry.getValue());
      }
    }
    return paramM;
  }
  
  @NotNull
  public static final <K, V> Map<K, V> filterValues(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull Function1<? super V, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      if (((Boolean)paramFunction1.invoke(localEntry.getValue())).booleanValue()) {
        localLinkedHashMap.put(localEntry.getKey(), localEntry.getValue());
      }
    }
    return (Map)localLinkedHashMap;
  }
  
  @InlineOnly
  private static final <K, V> V get(@NotNull Map<? extends K, ? extends V> paramMap, K paramK)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    return paramMap.get(paramK);
  }
  
  @InlineOnly
  private static final <K, V> V getOrElse(@NotNull Map<K, ? extends V> paramMap, K paramK, Function0<? extends V> paramFunction0)
  {
    paramMap = paramMap.get(paramK);
    if (paramMap != null) {
      return paramMap;
    }
    return paramFunction0.invoke();
  }
  
  public static final <K, V> V getOrElseNullable(@NotNull Map<K, ? extends V> paramMap, K paramK, @NotNull Function0<? extends V> paramFunction0)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction0, "defaultValue");
    Object localObject = paramMap.get(paramK);
    if ((localObject == null) && (!paramMap.containsKey(paramK))) {
      return paramFunction0.invoke();
    }
    return localObject;
  }
  
  public static final <K, V> V getOrPut(@NotNull Map<K, V> paramMap, K paramK, @NotNull Function0<? extends V> paramFunction0)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction0, "defaultValue");
    Object localObject2 = paramMap.get(paramK);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = paramFunction0.invoke();
      paramMap.put(paramK, localObject1);
    }
    return localObject1;
  }
  
  @SinceKotlin(version="1.1")
  public static final <K, V> V getValue(@NotNull Map<K, ? extends V> paramMap, K paramK)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    return MapsKt.getOrImplicitDefaultNullable(paramMap, paramK);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <K, V> HashMap<K, V> hashMapOf()
  {
    return new HashMap();
  }
  
  @NotNull
  public static final <K, V> HashMap<K, V> hashMapOf(@NotNull Pair<? extends K, ? extends V>... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "pairs");
    HashMap localHashMap = new HashMap(MapsKt.mapCapacity(((Object[])paramVarArgs).length));
    MapsKt.putAll((Map)localHashMap, paramVarArgs);
    return localHashMap;
  }
  
  @InlineOnly
  private static final <K, V> boolean isNotEmpty(@NotNull Map<? extends K, ? extends V> paramMap)
  {
    return paramMap.isEmpty() ^ true;
  }
  
  @InlineOnly
  private static final <K, V> Iterator<Map.Entry<K, V>> iterator(@NotNull Map<? extends K, ? extends V> paramMap)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    return paramMap.entrySet().iterator();
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <K, V> LinkedHashMap<K, V> linkedMapOf()
  {
    return new LinkedHashMap();
  }
  
  @NotNull
  public static final <K, V> LinkedHashMap<K, V> linkedMapOf(@NotNull Pair<? extends K, ? extends V>... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "pairs");
    return (LinkedHashMap)MapsKt.toMap(paramVarArgs, (Map)new LinkedHashMap(MapsKt.mapCapacity(((Object[])paramVarArgs).length)));
  }
  
  @PublishedApi
  public static final int mapCapacity(int paramInt)
  {
    if (paramInt < 3) {
      return paramInt + 1;
    }
    if (paramInt < 1073741824) {
      return paramInt + paramInt / 3;
    }
    return Integer.MAX_VALUE;
  }
  
  @NotNull
  public static final <K, V, R> Map<R, V> mapKeys(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    Map localMap = (Map)new LinkedHashMap(MapsKt.mapCapacity(paramMap.size()));
    paramMap = ((Iterable)paramMap.entrySet()).iterator();
    while (paramMap.hasNext())
    {
      Object localObject = paramMap.next();
      localMap.put(paramFunction1.invoke(localObject), ((Map.Entry)localObject).getValue());
    }
    return localMap;
  }
  
  @NotNull
  public static final <K, V, R, M extends Map<? super R, ? super V>> M mapKeysTo(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull M paramM, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    paramMap = ((Iterable)paramMap.entrySet()).iterator();
    while (paramMap.hasNext())
    {
      Object localObject = paramMap.next();
      paramM.put(paramFunction1.invoke(localObject), ((Map.Entry)localObject).getValue());
    }
    return paramM;
  }
  
  @InlineOnly
  private static final <K, V> Map<K, V> mapOf()
  {
    return MapsKt.emptyMap();
  }
  
  @NotNull
  public static final <K, V> Map<K, V> mapOf(@NotNull Pair<? extends K, ? extends V> paramPair)
  {
    Intrinsics.checkParameterIsNotNull(paramPair, "pair");
    paramPair = Collections.singletonMap(paramPair.getFirst(), paramPair.getSecond());
    Intrinsics.checkExpressionValueIsNotNull(paramPair, "java.util.Collections.si…(pair.first, pair.second)");
    return paramPair;
  }
  
  @NotNull
  public static final <K, V> Map<K, V> mapOf(@NotNull Pair<? extends K, ? extends V>... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "pairs");
    Object[] arrayOfObject = (Object[])paramVarArgs;
    if (arrayOfObject.length > 0) {
      return MapsKt.toMap(paramVarArgs, (Map)new LinkedHashMap(MapsKt.mapCapacity(arrayOfObject.length)));
    }
    return MapsKt.emptyMap();
  }
  
  @NotNull
  public static final <K, V, R> Map<K, R> mapValues(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    Map localMap = (Map)new LinkedHashMap(MapsKt.mapCapacity(paramMap.size()));
    paramMap = ((Iterable)paramMap.entrySet()).iterator();
    while (paramMap.hasNext())
    {
      Object localObject = paramMap.next();
      localMap.put(((Map.Entry)localObject).getKey(), paramFunction1.invoke(localObject));
    }
    return localMap;
  }
  
  @NotNull
  public static final <K, V, R, M extends Map<? super K, ? super R>> M mapValuesTo(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull M paramM, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    paramMap = ((Iterable)paramMap.entrySet()).iterator();
    while (paramMap.hasNext())
    {
      Object localObject = paramMap.next();
      paramM.put(((Map.Entry)localObject).getKey(), paramFunction1.invoke(localObject));
    }
    return paramM;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull Iterable<? extends K> paramIterable)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIterable, "keys");
    paramMap = MapsKt.toMutableMap(paramMap);
    CollectionsKt.removeAll((Collection)paramMap.keySet(), paramIterable);
    return MapsKt.optimizeReadOnlyMap(paramMap);
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> paramMap, K paramK)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    paramMap = MapsKt.toMutableMap(paramMap);
    paramMap.remove(paramK);
    return MapsKt.optimizeReadOnlyMap(paramMap);
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull Sequence<? extends K> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramSequence, "keys");
    paramMap = MapsKt.toMutableMap(paramMap);
    CollectionsKt.removeAll((Collection)paramMap.keySet(), paramSequence);
    return MapsKt.optimizeReadOnlyMap(paramMap);
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull K[] paramArrayOfK)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayOfK, "keys");
    paramMap = MapsKt.toMutableMap(paramMap);
    CollectionsKt.removeAll((Collection)paramMap.keySet(), paramArrayOfK);
    return MapsKt.optimizeReadOnlyMap(paramMap);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <K, V> void minusAssign(@NotNull Map<K, V> paramMap, Iterable<? extends K> paramIterable)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    CollectionsKt.removeAll((Collection)paramMap.keySet(), paramIterable);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <K, V> void minusAssign(@NotNull Map<K, V> paramMap, K paramK)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    paramMap.remove(paramK);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <K, V> void minusAssign(@NotNull Map<K, V> paramMap, Sequence<? extends K> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    CollectionsKt.removeAll((Collection)paramMap.keySet(), paramSequence);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <K, V> void minusAssign(@NotNull Map<K, V> paramMap, K[] paramArrayOfK)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    CollectionsKt.removeAll((Collection)paramMap.keySet(), paramArrayOfK);
  }
  
  @InlineOnly
  @JvmName(name="mutableIterator")
  private static final <K, V> Iterator<Map.Entry<K, V>> mutableIterator(@NotNull Map<K, V> paramMap)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    return paramMap.entrySet().iterator();
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <K, V> Map<K, V> mutableMapOf()
  {
    return (Map)new LinkedHashMap();
  }
  
  @NotNull
  public static final <K, V> Map<K, V> mutableMapOf(@NotNull Pair<? extends K, ? extends V>... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "pairs");
    Map localMap = (Map)new LinkedHashMap(MapsKt.mapCapacity(((Object[])paramVarArgs).length));
    MapsKt.putAll(localMap, paramVarArgs);
    return localMap;
  }
  
  @NotNull
  public static final <K, V> Map<K, V> optimizeReadOnlyMap(@NotNull Map<K, ? extends V> paramMap)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    switch (paramMap.size())
    {
    default: 
      return paramMap;
    case 1: 
      return MapsKt.toSingletonMap(paramMap);
    }
    return MapsKt.emptyMap();
  }
  
  @InlineOnly
  private static final <K, V> Map<K, V> orEmpty(@Nullable Map<K, ? extends V> paramMap)
  {
    if (paramMap != null) {
      return paramMap;
    }
    return MapsKt.emptyMap();
  }
  
  @NotNull
  public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull Iterable<? extends Pair<? extends K, ? extends V>> paramIterable)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIterable, "pairs");
    if (paramMap.isEmpty()) {
      return MapsKt.toMap(paramIterable);
    }
    paramMap = (Map)new LinkedHashMap(paramMap);
    MapsKt.putAll(paramMap, paramIterable);
    return paramMap;
  }
  
  @NotNull
  public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> paramMap1, @NotNull Map<? extends K, ? extends V> paramMap2)
  {
    Intrinsics.checkParameterIsNotNull(paramMap1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramMap2, "map");
    paramMap1 = new LinkedHashMap(paramMap1);
    paramMap1.putAll(paramMap2);
    return (Map)paramMap1;
  }
  
  @NotNull
  public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull Pair<? extends K, ? extends V> paramPair)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramPair, "pair");
    if (paramMap.isEmpty()) {
      return MapsKt.mapOf(paramPair);
    }
    paramMap = new LinkedHashMap(paramMap);
    paramMap.put(paramPair.getFirst(), paramPair.getSecond());
    return (Map)paramMap;
  }
  
  @NotNull
  public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull Sequence<? extends Pair<? extends K, ? extends V>> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramSequence, "pairs");
    paramMap = (Map)new LinkedHashMap(paramMap);
    MapsKt.putAll(paramMap, paramSequence);
    return MapsKt.optimizeReadOnlyMap(paramMap);
  }
  
  @NotNull
  public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull Pair<? extends K, ? extends V>[] paramArrayOfPair)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayOfPair, "pairs");
    if (paramMap.isEmpty()) {
      return MapsKt.toMap(paramArrayOfPair);
    }
    paramMap = (Map)new LinkedHashMap(paramMap);
    MapsKt.putAll(paramMap, paramArrayOfPair);
    return paramMap;
  }
  
  @InlineOnly
  private static final <K, V> void plusAssign(@NotNull Map<? super K, ? super V> paramMap, Iterable<? extends Pair<? extends K, ? extends V>> paramIterable)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    MapsKt.putAll(paramMap, paramIterable);
  }
  
  @InlineOnly
  private static final <K, V> void plusAssign(@NotNull Map<? super K, ? super V> paramMap, Map<K, ? extends V> paramMap1)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    paramMap.putAll(paramMap1);
  }
  
  @InlineOnly
  private static final <K, V> void plusAssign(@NotNull Map<? super K, ? super V> paramMap, Pair<? extends K, ? extends V> paramPair)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    paramMap.put(paramPair.getFirst(), paramPair.getSecond());
  }
  
  @InlineOnly
  private static final <K, V> void plusAssign(@NotNull Map<? super K, ? super V> paramMap, Sequence<? extends Pair<? extends K, ? extends V>> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    MapsKt.putAll(paramMap, paramSequence);
  }
  
  @InlineOnly
  private static final <K, V> void plusAssign(@NotNull Map<? super K, ? super V> paramMap, Pair<? extends K, ? extends V>[] paramArrayOfPair)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    MapsKt.putAll(paramMap, paramArrayOfPair);
  }
  
  public static final <K, V> void putAll(@NotNull Map<? super K, ? super V> paramMap, @NotNull Iterable<? extends Pair<? extends K, ? extends V>> paramIterable)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIterable, "pairs");
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      Pair localPair = (Pair)paramIterable.next();
      paramMap.put(localPair.component1(), localPair.component2());
    }
  }
  
  public static final <K, V> void putAll(@NotNull Map<? super K, ? super V> paramMap, @NotNull Sequence<? extends Pair<? extends K, ? extends V>> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramSequence, "pairs");
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Pair localPair = (Pair)paramSequence.next();
      paramMap.put(localPair.component1(), localPair.component2());
    }
  }
  
  public static final <K, V> void putAll(@NotNull Map<? super K, ? super V> paramMap, @NotNull Pair<? extends K, ? extends V>[] paramArrayOfPair)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayOfPair, "pairs");
    int i = 0;
    int j = paramArrayOfPair.length;
    while (i < j)
    {
      Pair<? extends K, ? extends V> localPair = paramArrayOfPair[i];
      paramMap.put(localPair.component1(), localPair.component2());
      i += 1;
    }
  }
  
  @InlineOnly
  private static final <K, V> V remove(@NotNull Map<? extends K, V> paramMap, K paramK)
  {
    if (paramMap == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
    }
    return TypeIntrinsics.asMutableMap(paramMap).remove(paramK);
  }
  
  @InlineOnly
  private static final <K, V> void set(@NotNull Map<K, V> paramMap, K paramK, V paramV)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    paramMap.put(paramK, paramV);
  }
  
  @NotNull
  public static final <K, V> Map<K, V> toMap(@NotNull Iterable<? extends Pair<? extends K, ? extends V>> paramIterable)
  {
    Intrinsics.checkParameterIsNotNull(paramIterable, "$receiver");
    if ((paramIterable instanceof Collection))
    {
      Collection localCollection = (Collection)paramIterable;
      switch (localCollection.size())
      {
      default: 
        return MapsKt.toMap(paramIterable, (Map)new LinkedHashMap(MapsKt.mapCapacity(localCollection.size())));
      case 1: 
        if ((paramIterable instanceof List)) {}
        for (paramIterable = ((List)paramIterable).get(0);; paramIterable = paramIterable.iterator().next())
        {
          paramIterable = (Pair)paramIterable;
          break;
        }
        return MapsKt.mapOf(paramIterable);
      }
      return MapsKt.emptyMap();
    }
    return MapsKt.optimizeReadOnlyMap(MapsKt.toMap(paramIterable, (Map)new LinkedHashMap()));
  }
  
  @NotNull
  public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Iterable<? extends Pair<? extends K, ? extends V>> paramIterable, @NotNull M paramM)
  {
    Intrinsics.checkParameterIsNotNull(paramIterable, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    MapsKt.putAll(paramM, paramIterable);
    return paramM;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <K, V> Map<K, V> toMap(@NotNull Map<? extends K, ? extends V> paramMap)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    switch (paramMap.size())
    {
    default: 
      return MapsKt.toMutableMap(paramMap);
    case 1: 
      return MapsKt.toSingletonMap(paramMap);
    }
    return MapsKt.emptyMap();
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull M paramM)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    paramM.putAll(paramMap);
    return paramM;
  }
  
  @NotNull
  public static final <K, V> Map<K, V> toMap(@NotNull Sequence<? extends Pair<? extends K, ? extends V>> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return MapsKt.optimizeReadOnlyMap(MapsKt.toMap(paramSequence, (Map)new LinkedHashMap()));
  }
  
  @NotNull
  public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Sequence<? extends Pair<? extends K, ? extends V>> paramSequence, @NotNull M paramM)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    MapsKt.putAll(paramM, paramSequence);
    return paramM;
  }
  
  @NotNull
  public static final <K, V> Map<K, V> toMap(@NotNull Pair<? extends K, ? extends V>[] paramArrayOfPair)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfPair, "$receiver");
    Object[] arrayOfObject = (Object[])paramArrayOfPair;
    switch (arrayOfObject.length)
    {
    default: 
      return MapsKt.toMap(paramArrayOfPair, (Map)new LinkedHashMap(MapsKt.mapCapacity(arrayOfObject.length)));
    case 1: 
      return MapsKt.mapOf(paramArrayOfPair[0]);
    }
    return MapsKt.emptyMap();
  }
  
  @NotNull
  public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Pair<? extends K, ? extends V>[] paramArrayOfPair, @NotNull M paramM)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfPair, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    MapsKt.putAll(paramM, paramArrayOfPair);
    return paramM;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <K, V> Map<K, V> toMutableMap(@NotNull Map<? extends K, ? extends V> paramMap)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    return (Map)new LinkedHashMap(paramMap);
  }
  
  @InlineOnly
  private static final <K, V> Pair<K, V> toPair(@NotNull Map.Entry<? extends K, ? extends V> paramEntry)
  {
    return new Pair(paramEntry.getKey(), paramEntry.getValue());
  }
  
  @NotNull
  public static final <K, V> Map<K, V> toSingletonMap(@NotNull Map<? extends K, ? extends V> paramMap)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    paramMap = (Map.Entry)paramMap.entrySet().iterator().next();
    paramMap = Collections.singletonMap(paramMap.getKey(), paramMap.getValue());
    Intrinsics.checkExpressionValueIsNotNull(paramMap, "java.util.Collections.singletonMap(key, value)");
    Intrinsics.checkExpressionValueIsNotNull(paramMap, "with (entries.iterator()…ingletonMap(key, value) }");
    return paramMap;
  }
  
  @InlineOnly
  private static final <K, V> Map<K, V> toSingletonMapOrSelf(@NotNull Map<K, ? extends V> paramMap)
  {
    return MapsKt.toSingletonMap(paramMap);
  }
}
