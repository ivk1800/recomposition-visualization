package ru.ivk1800

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import ru.ivk1800.resources.Res
import ru.ivk1800.resources.avoid_unnecessary_state_reads_1_good_explanation
import ru.ivk1800.resources.avoid_unnecessary_state_reads_1_good_title
import ru.ivk1800.resources.avoid_unnecessary_state_reads_2_good_explanation
import ru.ivk1800.resources.avoid_unnecessary_state_reads_2_good_title
import ru.ivk1800.resources.avoid_unnecessary_state_reads_bad_explanation
import ru.ivk1800.resources.avoid_unnecessary_state_reads_bad_title
import ru.ivk1800.resources.composition_local_explanation
import ru.ivk1800.resources.composition_local_title
import ru.ivk1800.resources.derived_state_of_bad_explanation
import ru.ivk1800.resources.derived_state_of_bad_title
import ru.ivk1800.resources.derived_state_of_explanation
import ru.ivk1800.resources.derived_state_of_title
import ru.ivk1800.resources.immutable_type_explanation
import ru.ivk1800.resources.immutable_type_title
import ru.ivk1800.resources.lambda_modifiers_good_explanation
import ru.ivk1800.resources.lambda_modifiers_good_title
import ru.ivk1800.resources.launch_effect_key_bad_explanation
import ru.ivk1800.resources.launch_effect_key_bad_title
import ru.ivk1800.resources.launch_effect_key_good_explanation
import ru.ivk1800.resources.launch_effect_key_good_title
import ru.ivk1800.resources.launch_effect_with_key_explanation
import ru.ivk1800.resources.launch_effect_with_key_title
import ru.ivk1800.resources.launch_effect_without_key_explanation
import ru.ivk1800.resources.launch_effect_without_key_title
import ru.ivk1800.resources.lazy_list_with_key_good
import ru.ivk1800.resources.lazy_list_with_key_good_explanation
import ru.ivk1800.resources.lazy_list_without_key_bad_explanation
import ru.ivk1800.resources.lazy_list_without_key_bad_title
import ru.ivk1800.resources.movable_content_of_explanation
import ru.ivk1800.resources.movable_content_of_title
import ru.ivk1800.resources.not_lambda_modifiers_bad_explanation
import ru.ivk1800.resources.not_lambda_modifiers_bad_title
import ru.ivk1800.resources.read_state_bad_explanation
import ru.ivk1800.resources.read_state_bad_title
import ru.ivk1800.resources.read_state_good_explanation
import ru.ivk1800.resources.read_state_good_title
import ru.ivk1800.resources.static_composition_local_explanation
import ru.ivk1800.resources.static_composition_local_title
import ru.ivk1800.resources.unstable_type_explanation
import ru.ivk1800.resources.unstable_type_title
import ru.ivk1800.resources.with_remember_updated_state_explanation
import ru.ivk1800.resources.with_remember_updated_state_title
import ru.ivk1800.resources.without_remember_updated_state_explanation
import ru.ivk1800.resources.without_remember_updated_state_title
import ru.ivk1800.sample.AvoidUnnecessaryStateReadsBadSample
import ru.ivk1800.sample.AvoidUnnecessaryStateReadsGood1Sample
import ru.ivk1800.sample.AvoidUnnecessaryStateReadsGood2Sample
import ru.ivk1800.sample.CompositionLocalSample
import ru.ivk1800.sample.DerivedStateOfSample
import ru.ivk1800.sample.ImmutableTypeGoodSample
import ru.ivk1800.sample.LambdaModifiersGoodSample
import ru.ivk1800.sample.LaunchEffectKeyBadSample
import ru.ivk1800.sample.LaunchEffectKeyGoodSample
import ru.ivk1800.sample.LaunchEffectWithKeySample
import ru.ivk1800.sample.LaunchEffectWithoutKeySample
import ru.ivk1800.sample.LazyListWithKeyGoodSample
import ru.ivk1800.sample.LazyListWithoutKeyBadSample
import ru.ivk1800.sample.MovableContentOfSample
import ru.ivk1800.sample.NotLambdaModifiersGoodSample
import ru.ivk1800.sample.ReadStateBadSample
import ru.ivk1800.sample.ReadStateGoodSample
import ru.ivk1800.sample.StaticCompositionLocalSample
import ru.ivk1800.sample.UnstableTypeBadSample
import ru.ivk1800.sample.WithRememberUpdatedStateSample
import ru.ivk1800.sample.WithoutDerivedStateOfSample
import ru.ivk1800.sample.WithoutRememberUpdatedStateSample

@Serializable
enum class SampleDescriptor(
    val id: Int,
    val entryPoint: @Composable () -> Unit,
    val sourceCode: @Composable () -> Unit,
    val title: StringResource,
    val explanation: StringResource?,
    val good: SampleDescriptor?,
) {
    CompositionLocal(
        id = 1,
        entryPoint = { CompositionLocalSample.EntryPoint() },
        sourceCode = { CompositionLocalSample.HighlightedSourceCode() },
        title = Res.string.composition_local_title,
        explanation = Res.string.composition_local_explanation,
        good = null,
    ),
    StaticCompositionLocal(
        id = 2,
        entryPoint = { StaticCompositionLocalSample.EntryPoint() },
        sourceCode = { StaticCompositionLocalSample.HighlightedSourceCode() },
        title = Res.string.static_composition_local_title,
        explanation = Res.string.static_composition_local_explanation,
        good = CompositionLocal,
    ),
    LambdaModifiersGood(
        id = 3,
        entryPoint = { LambdaModifiersGoodSample.EntryPoint() },
        sourceCode = { LambdaModifiersGoodSample.HighlightedSourceCode() },
        title = Res.string.lambda_modifiers_good_title,
        explanation = Res.string.lambda_modifiers_good_explanation,
        good = null,
    ),
    NotLambdaModifiersBad(
        id = 4,
        entryPoint = { NotLambdaModifiersGoodSample.EntryPoint() },
        sourceCode = { NotLambdaModifiersGoodSample.HighlightedSourceCode() },
        title = Res.string.not_lambda_modifiers_bad_title,
        explanation = Res.string.not_lambda_modifiers_bad_explanation,
        good = LambdaModifiersGood,
    ),
    AvoidUnnecessaryStateReadsGood2(
        id = 5,
        entryPoint = { AvoidUnnecessaryStateReadsGood2Sample.EntryPoint() },
        sourceCode = { AvoidUnnecessaryStateReadsGood2Sample.HighlightedSourceCode() },
        title = Res.string.avoid_unnecessary_state_reads_2_good_title,
        explanation = Res.string.avoid_unnecessary_state_reads_2_good_explanation,
        good = null,
    ),
    AvoidUnnecessaryStateReadsGood1(
        id = 6,
        entryPoint = { AvoidUnnecessaryStateReadsGood1Sample.EntryPoint() },
        sourceCode = { AvoidUnnecessaryStateReadsGood1Sample.HighlightedSourceCode() },
        title = Res.string.avoid_unnecessary_state_reads_1_good_title,
        explanation = Res.string.avoid_unnecessary_state_reads_1_good_explanation,
        good = null,
    ),
    AvoidUnnecessaryStateReadsBad(
        id = 7,
        entryPoint = { AvoidUnnecessaryStateReadsBadSample.EntryPoint() },
        sourceCode = { AvoidUnnecessaryStateReadsBadSample.HighlightedSourceCode() },
        title = Res.string.avoid_unnecessary_state_reads_bad_title,
        explanation = Res.string.avoid_unnecessary_state_reads_bad_explanation,
        good = AvoidUnnecessaryStateReadsGood1,
    ),
    DerivedStateOf(
        id = 8,
        entryPoint = { DerivedStateOfSample.EntryPoint() },
        sourceCode = { DerivedStateOfSample.HighlightedSourceCode() },
        title = Res.string.derived_state_of_title,
        explanation = Res.string.derived_state_of_explanation,
        good = null,
    ),
    WithoutDerivedStateOf(
        id = 16,
        entryPoint = { WithoutDerivedStateOfSample.EntryPoint() },
        sourceCode = { WithoutDerivedStateOfSample.HighlightedSourceCode() },
        title = Res.string.derived_state_of_bad_title,
        explanation = Res.string.derived_state_of_bad_explanation,
        good = DerivedStateOf,
    ),
    ReadStateGood(
        id = 9,
        entryPoint = { ReadStateGoodSample.EntryPoint() },
        sourceCode = { ReadStateGoodSample.HighlightedSourceCode() },
        title = Res.string.read_state_good_title,
        explanation = Res.string.read_state_good_explanation,
        good = null,
    ),
    ReadStateBad(
        id = 10,
        entryPoint = { ReadStateBadSample.EntryPoint() },
        sourceCode = { ReadStateBadSample.HighlightedSourceCode() },
        title = Res.string.read_state_bad_title,
        explanation = Res.string.read_state_bad_explanation,
        good = ReadStateGood,
    ),
    StableType(
        id = 12,
        entryPoint = { ImmutableTypeGoodSample.EntryPoint() },
        sourceCode = { ImmutableTypeGoodSample.HighlightedSourceCode() },
        title = Res.string.immutable_type_title,
        explanation = Res.string.immutable_type_explanation,
        good = null,
    ),
    UnstableType(
        id = 11,
        entryPoint = { UnstableTypeBadSample.EntryPoint() },
        sourceCode = { UnstableTypeBadSample.HighlightedSourceCode() },
        title = Res.string.unstable_type_title,
        explanation = Res.string.unstable_type_explanation,
        good = StableType,
    ),
    MovableContentOf(
        id = 12,
        entryPoint = { MovableContentOfSample.EntryPoint() },
        sourceCode = { MovableContentOfSample.HighlightedSourceCode() },
        title = Res.string.movable_content_of_title,
        explanation = Res.string.movable_content_of_explanation,
        good = null,
    ),
    LaunchEffectKeyGood(
        id = 14,
        entryPoint = { LaunchEffectKeyGoodSample.EntryPoint() },
        sourceCode = { LaunchEffectKeyGoodSample.HighlightedSourceCode() },
        title = Res.string.launch_effect_key_good_title,
        explanation = Res.string.launch_effect_key_good_explanation,
        good = null,
    ),
    LaunchEffectKeyBad(
        id = 13,
        entryPoint = { LaunchEffectKeyBadSample.EntryPoint() },
        sourceCode = { LaunchEffectKeyBadSample.HighlightedSourceCode() },
        title = Res.string.launch_effect_key_bad_title,
        explanation = Res.string.launch_effect_key_bad_explanation,
        good = LaunchEffectKeyGood,
    ),
    LazyListWithKey(
        id = 14,
        entryPoint = { LazyListWithKeyGoodSample.EntryPoint() },
        sourceCode = { LazyListWithKeyGoodSample.HighlightedSourceCode() },
        title = Res.string.lazy_list_with_key_good,
        explanation = Res.string.lazy_list_with_key_good_explanation,
        good = null,
    ),
    LazyListWithoutKey(
        id = 15,
        entryPoint = { LazyListWithoutKeyBadSample.EntryPoint() },
        sourceCode = { LazyListWithoutKeyBadSample.HighlightedSourceCode() },
        title = Res.string.lazy_list_without_key_bad_title,
        explanation = Res.string.lazy_list_without_key_bad_explanation,
        good = LazyListWithKey,
    ),
    WithRememberUpdatedState(
        id = 17,
        entryPoint = { WithRememberUpdatedStateSample.EntryPoint() },
        sourceCode = { WithRememberUpdatedStateSample.HighlightedSourceCode() },
        title = Res.string.with_remember_updated_state_title,
        explanation = Res.string.with_remember_updated_state_explanation,
        good = null,
    ),
    WithoutRememberUpdatedState(
        id = 18,
        entryPoint = { WithoutRememberUpdatedStateSample.EntryPoint() },
        sourceCode = { WithoutRememberUpdatedStateSample.HighlightedSourceCode() },
        title = Res.string.without_remember_updated_state_title,
        explanation = Res.string.without_remember_updated_state_explanation,
        good = WithRememberUpdatedState,
    ),
    LaunchEffectWithKey(
        id = 19,
        entryPoint = { LaunchEffectWithKeySample.EntryPoint() },
        sourceCode = { LaunchEffectWithKeySample.HighlightedSourceCode() },
        title = Res.string.launch_effect_with_key_title,
        explanation = Res.string.launch_effect_with_key_explanation,
        good = null,
    ),
    LaunchEffectWithoutKey(
        id = 20,
        entryPoint = { LaunchEffectWithoutKeySample.EntryPoint() },
        sourceCode = { LaunchEffectWithoutKeySample.HighlightedSourceCode() },
        title = Res.string.launch_effect_without_key_title,
        explanation = Res.string.launch_effect_without_key_explanation,
        good = LaunchEffectWithKey,
    ),
}
