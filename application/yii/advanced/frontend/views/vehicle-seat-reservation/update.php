<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\vehicleseatreservation */

$this->title = 'Update Vehicleseatreservation: ' . $model->vehicle_id;
$this->params['breadcrumbs'][] = ['label' => 'Vehicleseatreservations', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->vehicle_id, 'url' => ['view', 'vehicle_id' => $model->vehicle_id, 'seat_reservation_id' => $model->seat_reservation_id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="vehicleseatreservation-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
