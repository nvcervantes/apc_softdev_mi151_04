<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\vehicleseatreservation */

$this->title = 'Create Vehicleseatreservation';
$this->params['breadcrumbs'][] = ['label' => 'Vehicleseatreservations', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="vehicleseatreservation-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
